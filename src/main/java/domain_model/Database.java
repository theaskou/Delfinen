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

    public void createMember(int memberID, String name, LocalDate birthday, String address, String email, boolean isOnCompetitionTeam, boolean isActive, LocalDate subscriptionDate) {
        memberlist.add(new Member(memberID, name, birthday, address, email, isOnCompetitionTeam, isActive,subscriptionDate));
        fh.saveMemberData(memberlist, CSVPath);
    }

    public ArrayList<Result> junoirTeamFilter(){
        ArrayList<Result> juniorTeam = new ArrayList<>();
        for (Result result : resultList) {
            if (result.getBirthday().isAfter(LocalDate.now().minusYears(18)))
                juniorTeam.add(result);
        } return juniorTeam;
    }

    public ArrayList<Result> seniorTeamFilter(){
        ArrayList<Result> seniorTeam = new ArrayList<>();
        for (Result result : resultList) {
            if (result.getBirthday().isBefore(LocalDate.now().minusYears(18)))
                seniorTeam.add(result);
        } return seniorTeam;
    }

    public ArrayList<Result> crawlResultsFilter(ArrayList<Result> juniorOrSeniorList) {
        ArrayList<Result> crawlResults = new ArrayList<>();
        for (Result result : juniorOrSeniorList) {
            if (result.getSwimmingDiscipline().equals(SwimmingDiscipline.CRAWL) && result.getBirthday().isBefore(LocalDate.now().minusYears(18)))
            crawlResults.add(result);
        }
        return crawlResults;
    }

    public ArrayList<Result> backStrokeResultFilter(ArrayList<Result> juniorOrSeniorList){
        ArrayList<Result> backStrokeResult = new ArrayList<>();
        for (Result result: juniorOrSeniorList) {
            if (result.getSwimmingDiscipline().equals(SwimmingDiscipline.BACKSTROKE))
                backStrokeResult.add(result);
        }
        return backStrokeResult;
    }


    public ArrayList<Result> breastStrokeResultFilter(ArrayList<Result> juniorOrSeniorList) {
        ArrayList<Result> breastStrokeResult = new ArrayList<>();
        for(Result result: juniorOrSeniorList) {
            if (result.getSwimmingDiscipline().equals(SwimmingDiscipline.BREASTSTROKE))
                breastStrokeResult.add(result);
        }
        return breastStrokeResult;
    }

    public ArrayList<Result>butterFlyResultFilter(ArrayList<Result> juniorOrSeniorList) {
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
        public ArrayList<Member> restanceList(){
            ArrayList<Member> restanceList = new ArrayList<>();
            for (Member member : memberlist){
                if (member.getSubscriptionDate().plusYears(1).isBefore(LocalDate.now()))
                    restanceList.add(member);
            }
            return restanceList;
        }


    public ResultCompareMessage createResult(Member member, SwimmingDiscipline disciplin, double newTime, LocalDate dato) {
        ResultCompareMessage resultCompareMessage = ResultCompareMessage.NOT_FOUND;
        Result resultOnFile = null;
        for (Result result : resultList){
            if (member.getMemberID() == result.getMemberID() && disciplin.equals(result.getSwimmingDiscipline())) {
                if (result.getBestTime() > newTime) {
                    resultOnFile = result;
                    resultCompareMessage = ResultCompareMessage.NEW_BEST_RESULT;
                } else {
                    resultCompareMessage = ResultCompareMessage.NOT_BEST_RESULT;
                }
            }
        }
        if (resultCompareMessage.equals(ResultCompareMessage.NEW_BEST_RESULT)){
                resultList.add(new Result(member.getMemberID(), member.getName(), member.getBirthday(), disciplin, newTime, dato));
                resultList.remove(resultOnFile);
                fh.saveResultatData(resultList, CSVPathResultData);

        }
        return resultCompareMessage;
    }
}