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


    //TODO: Save member to file
    public Database() {
        this.fh = new FileHandler();
        this.memberlist = fh.loadMemberData(CSVPath);
        this.resultList = fh.loadResultData(CSVPathResultData);
    }

    public void createMember(int memberID, String name, LocalDate birthday, String address, String email, boolean isOnCompetitionTeam, boolean isActive) {
        memberlist.add(new Member(memberID, name, birthday, address, email, isOnCompetitionTeam, isActive));
        fh.saveMemberData(memberlist, CSVPath);
    }

    public void createResult(int memberID, String name, LocalDate birthday, SwimmingDiscipline svømmediscipliner, double bestTime, LocalDate date) {
        resultList.add(new Result(memberID, name, birthday, svømmediscipliner, bestTime, date));
        fh.saveResultatData(resultList, CSVPathResultData);
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

    // Liste over resultater
    public ArrayList<Result> getResultList() {
        return resultList;
    }


    // Liste over alle Junior konkurrence medlemmer
    public ArrayList<Member> youthTeam() {
        ArrayList<Member> youthTeam = new ArrayList<>();
        for (Member member : memberlist) {
            if (LocalDate.now().minusYears(18).isBefore(member.getBirthday()) && member.isOnCompetitionTeam() == true) {
                youthTeam.add(member);
            }
        }
        return youthTeam;
    }

    //Liste over alle senior konkurrence medlemmer
    public ArrayList<Member> seniorTeam() {
        ArrayList<Member> seniorTeam = new ArrayList<>();
        for (Member member : memberlist) {
            if (LocalDate.now().minusYears(18).isAfter(member.getBirthday()) && member.isOnCompetitionTeam() == true) {
                seniorTeam.add(member);
            }
        }
        return seniorTeam;
    }

    //bliver ikke brugt lige nu
    public void save() {
        fh.saveMemberData(memberlist, CSVPath);
    }

    //Udprint af alle medlemmer
    public void printMemberlist() {
        for (Member member : memberlist) {
            System.out.println(member);
        }
    }

    //kun til test
    public void printResults() {
        for (Result results : resultList) {
            System.out.println(results);
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

}