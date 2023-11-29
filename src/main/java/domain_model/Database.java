package domain_model;

import java.time.LocalDate;

import data_source.FileHandler;

import java.util.ArrayList;

public class Database {
    private ArrayList<Member> memberlist;
    private ArrayList<Resultat> resultList = new ArrayList<>();
    private FileHandler fh;
    private File CSVPath;
    private Member member;


    //TODO: Save member to file
    public Database(File CSVPath) {
        this.CSVPath = CSVPath;
        this.fh = new FileHandler();
        this.memberlist = fh.loadMemberData(CSVPath);

    }

    public void createMember(int memberID, String name, LocalDate birthday, String address, String email, boolean isOnCompetitionTeam, boolean isActive) {
        memberlist.add(new Member(memberID, name, birthday, address, email, isOnCompetitionTeam, isActive));
        fh.saveMemberData(memberlist, CSVPath);

    }


    public void createResult(int memberID, String name, Svømmediscipliner svømmediscipliner, double bestTime, LocalDate date){
        resultList.add(new Resultat(memberID, name, svømmediscipliner, bestTime, date));
        //Save metode til resultaterne
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
    public void printResults(){
        for (Resultat results : resultList){
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