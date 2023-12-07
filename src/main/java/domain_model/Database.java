package domain_model;

import java.time.LocalDate;

import data_source.FileHandler;

import java.util.ArrayList;

public class Database {
    private ArrayList<Member> memberlist;
    private ArrayList<Result> resultList;
    private FileHandler fh;
    private String CSVPath = "memberData.csv";
    private String CSVPathResultData = "resultData.csv";


    public Database() {
        this.fh = new FileHandler();
        this.memberlist = fh.loadMemberData(CSVPath);
        this.resultList = fh.loadResultData(CSVPathResultData);
    }

    //Konstruktør til test
    public Database(String CSVPath, String CSVPathResultData) {
        this.fh = new FileHandler();
        this.CSVPath = CSVPath;
        this.CSVPathResultData = CSVPathResultData;
        this.memberlist = fh.loadMemberData(CSVPath);
        this.resultList = fh.loadResultData(CSVPathResultData);
    }

    public int getMemberlist(){
        return memberlist.size();
    }

    public int getResultatlist(){
        return resultList.size();
    }

    //Laver et medlem
    public void createMember(int memberID, String name, LocalDate birthday, String address, String email, boolean isOnCompetitionTeam, boolean isActive, LocalDate subscriptionDate) {
        memberlist.add(new Member(memberID, name, birthday, address, email, isOnCompetitionTeam, isActive, subscriptionDate));
        fh.saveMemberData(memberlist, CSVPath);
    }

    //Laver et resultat
    public ResultCompareMessage createResult(Member member, SwimmingDiscipline disciplin, double newTime, LocalDate dato) {
        ResultCompareMessage resultCompareMessage = ResultCompareMessage.NOT_FOUND;
        Result resultOnFile = null;
        for (Result result : resultList) {
            if (member.getMemberID() == result.getMemberID() && disciplin.equals(result.getSwimmingDiscipline())) {
                if (result.getBestTime() > newTime) {
                    resultOnFile = result;
                    resultCompareMessage = ResultCompareMessage.NEW_BEST_RESULT;
                } else {
                    resultCompareMessage = ResultCompareMessage.NOT_BEST_RESULT;
                }
            }
        }
        if(resultOnFile == null){
            resultList.add(new Result(member.getMemberID(), member.getName(), member.getBirthday(), disciplin, newTime, dato));
            resultCompareMessage = ResultCompareMessage.FIRST_TIME_RESULT;
            fh.saveResultatData(resultList, CSVPathResultData);
        }
        if (resultCompareMessage.equals(ResultCompareMessage.NEW_BEST_RESULT)) {
            resultList.add(new Result(member.getMemberID(), member.getName(), member.getBirthday(), disciplin, newTime, dato));
            resultList.remove(resultOnFile);
            fh.saveResultatData(resultList, CSVPathResultData);
        }
        return resultCompareMessage;
    }

    //Laver stævneresultat og saver til resultlist
    public void createCompetitionResult(Member member, SwimmingDiscipline discipline, double newTime, String competitionName, int rank, LocalDate date){
        resultList.add(new Result(member.getMemberID(), member.getName(), member.getBirthday(), discipline, newTime, competitionName, rank, date));
        fh.saveResultatData(resultList, CSVPathResultData);
    }

    //Gemmer medlemmer til memberlist
    public void saveMemberData(){
        fh.saveMemberData(memberlist, CSVPath);
    }

    //Filtrere for om du er junior
    public ArrayList<Result> juniorTeamFilter() {
        ArrayList<Result> juniorTeam = new ArrayList<>();
        for (Result result : resultList) {
            if (result.getBirthday().isAfter(LocalDate.now().minusYears(18)))
                juniorTeam.add(result);
        }
        return juniorTeam;
    }
    //Filtrere for om du er senior
    public ArrayList<Result> seniorTeamFilter() {
        ArrayList<Result> seniorTeam = new ArrayList<>();
        for (Result result : resultList) {
            if (result.getBirthday().isBefore(LocalDate.now().minusYears(18)))
                seniorTeam.add(result);
        }
        return seniorTeam;
    }
    //Laver en liste med crawlresultater for junior og senior
    public ArrayList<Result> crawlResultsFilter(ArrayList<Result> juniorOrSeniorList) {
        ArrayList<Result> crawlResults = new ArrayList<>();
        for (Result result : juniorOrSeniorList) {
            if (result.getSwimmingDiscipline().equals(SwimmingDiscipline.CRAWL) && result.getBirthday().isBefore(LocalDate.now().minusYears(18)))
                crawlResults.add(result);
        }
        return crawlResults;
    }

    //Laver en liste med backstroke for junior og senior
    public ArrayList<Result> backStrokeResultFilter(ArrayList<Result> juniorOrSeniorList) {
        ArrayList<Result> backStrokeResult = new ArrayList<>();
        for (Result result : juniorOrSeniorList) {
            if (result.getSwimmingDiscipline().equals(SwimmingDiscipline.BACKSTROKE))
                backStrokeResult.add(result);
        }
        return backStrokeResult;
    }

    //Laver en liste med breakstroke for junior og senior
    public ArrayList<Result> breastStrokeResultFilter(ArrayList<Result> juniorOrSeniorList) {
        ArrayList<Result> breastStrokeResult = new ArrayList<>();
        for (Result result : juniorOrSeniorList) {
            if (result.getSwimmingDiscipline().equals(SwimmingDiscipline.BREASTSTROKE))
                breastStrokeResult.add(result);
        }
        return breastStrokeResult;
    }

    //Laver en liste med butterflyresultater for junior og senior
    public ArrayList<Result> butterFlyResultFilter(ArrayList<Result> juniorOrSeniorList) {
        ArrayList<Result> butterFlyResult = new ArrayList<>();
        for (Result result : juniorOrSeniorList) {
            if (result.getSwimmingDiscipline().equals(SwimmingDiscipline.BUTTERFLY))
                butterFlyResult.add(result);
        }
        return butterFlyResult;
    }

    // Liste over alle medlemmer
    public ArrayList<Member> memberlist() {
        return memberlist;
    }

    // Liste over alle konkurrence medlemmer
    public ArrayList<Member> getCompetitionMember() {
        ArrayList<Member> competitionMembers = new ArrayList<>();
        for (Member member : memberlist) {
            if (member.isOnCompetitionTeam()) {
                competitionMembers.add(member);
            }
        }
        return competitionMembers;
    }

    //Udprint af alle medlemmer
    public void printMemberlist() {
        for (Member member : memberlist) {
            System.out.println(member);
        }
    }


    //Total kontingent beregner
    public int totalSubscription() {
        int totalSubscription = 0;
        for (Member member : memberlist) {
            totalSubscription += member.calculateSubscription();
        }
        return totalSubscription;
    }

    //Restance liste
    public ArrayList<Member> restanceList() {
        ArrayList<Member> restanceList = new ArrayList<>();
        for (Member member : memberlist) {
            if (member.getSubscriptionDate().plusYears(1).isBefore(LocalDate.now()))
                restanceList.add(member);
        }
        return restanceList;
    }

    //Printer stævneresultat
    public void printCompetitions() {
        for (Result resultsFromList : resultList) {
            if (resultsFromList.getRank() != 0) {
                System.out.println("Navn: " + resultsFromList.getName() + " Disciplin: " + swimmingdisciplinFormatter(resultsFromList.getSwimmingDiscipline()) + " Placering: " + resultsFromList.getRank() +
                        " Tid: " + resultsFromList.getBestTime() + " Sekunder" + " Stævne: " + resultsFromList.getCompetition() + "\n");
            }
        }
    }

    //Ændre svømmedisciplin enums fra engelsk til dansk
    public String swimmingdisciplinFormatter(SwimmingDiscipline discipline){
        String disciplinReturn = null;
        switch (discipline) {
            case BUTTERFLY -> {
                disciplinReturn = "Butterfly";
            }
            case CRAWL -> {
                disciplinReturn = "Crawl";
            }
            case BACKSTROKE -> {
                disciplinReturn ="Rygsvømning";
            }
            case BREASTSTROKE -> {
                disciplinReturn = "Brystsvømning";
            }
        } 
        return disciplinReturn;
    }

    public boolean deleteMember(int medlemsID) {
        boolean isdeleted = false;

        resultList.removeIf(result -> medlemsID == result.getMemberID());
        fh.saveResultatData(resultList, CSVPathResultData);

        for (Member member : memberlist) {
            if (medlemsID == member.getMemberID()) {
                memberlist.remove(member);
                fh.saveMemberData(memberlist, CSVPath);
                isdeleted = true;
                break;
            }
        } return isdeleted;
    }
}



