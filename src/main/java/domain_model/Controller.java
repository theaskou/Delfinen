package domain_model;

import java.time.LocalDate;
import java.util.ArrayList;


public class Controller {

    private Database db;

    public Controller() {
        db = new Database();

    }

    //Create member
    public void createMember(int memberID, String name, LocalDate birthday, String address,
                             String email, boolean isOnCompetitionTeam, boolean isActive) {
        db.createMember(memberID, name, birthday, address, email, isOnCompetitionTeam, isActive);
    }

    //Create result
    public void createResult(int memberID, String name, LocalDate birthday, SwimmingDiscipline svømmediscipliner,
                             double bestTime, LocalDate date) {

        db.createResult(memberID, name, birthday, svømmediscipliner, bestTime, date);
    }

    //Liste af alle medlemmer
    public ArrayList<Member> membersList() {
        return db.memberlist();
    }

    //Liste af resultater
    public ArrayList<Result> getResultList() {
        return db.getResultList();
    }

    //Liste af alle konkurrence medlemmer
    public ArrayList<Member> getCompetetionMember() {
        return db.getCompetitionMember();
    }

    //liste af youth konkurrencehold
    public ArrayList<Member> youthTeam() {
        return db.youthTeam();
    }

    //Liste af senior konkurrencehold
    public ArrayList<Member> seniorTeam() {
        return db.seniorTeam();
    }

    //Udprint af alle medlemmer
    public void printMembers() {
        db.printMemberlist();
    }

    //Kun til test - print resultater
    public void printResults() {
        db.printResults();
    }

    //Samlet kontingent udregner
    public int totalSubsription() {
        return db.totalSubscription();
    }

    //bliver ikke brugt lige nu
    public void save() {
        db.save();
    }

    public ArrayList<Result> crawlResultsFilter() {
        return db.crawlResultsFilter();
    }

}
