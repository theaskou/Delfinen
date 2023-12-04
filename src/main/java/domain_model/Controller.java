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
    public ResultCompareMessage createResult(Member chosenMember, SwimmingDiscipline chosenDisciplin, double resultat, LocalDate dato) {
        return db.createResult(chosenMember, chosenDisciplin, resultat, dato);
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

    // Filtreret liste, kun med junior resultater
    public ArrayList<Result> juniorTeamFilter() {
        return db.junoirTeamFilter();
    }

    // Filtreret liste, kun med senior resultater
    public ArrayList<Result> seniorTeamFilter(){
        return db.seniorTeamFilter();
    }

    // Filtreret liste, kun med crawl resultater
    public ArrayList<Result> crawlResultsFilter(ArrayList<Result> juniorOrSeniorList) {
        return db.crawlResultsFilter(juniorOrSeniorList);
    }

    // Filtreret liste, kun med backstroke resultater
    public ArrayList<Result> backStrokeResultFilter(ArrayList<Result> juniorOrSeniorList){
        return db.backStrokeResultFilter(juniorOrSeniorList);
    }

    // Filtreret liste, kun med butterfly resultater
    public ArrayList<Result> butterflyResultFilter(ArrayList<Result> juniorOrSeniorList) {
        return db.butterFlyResultFilter(juniorOrSeniorList);
    }

    // Filtreret liste, kun med breaststroke resultater
    public ArrayList<Result> breaststrokeResultFilter(ArrayList<Result> juniorOrSeniorList) {
        return db.breastStrokeResultFilter(juniorOrSeniorList);
    }

    public ArrayList<Member> restanceList(){
        return db.restanceList();
    }




}
