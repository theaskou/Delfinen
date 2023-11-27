package domain_model;

import data_source.FileHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.io.File;


public class Controller {
    File CSVPath;
    private Database db;
    private FileHandler fh;
    private MemberManager memberManager = new MemberManager();

    public Controller(){
        CSVPath = new File("memberData.csv");
        db = new Database(CSVPath);
        fh = new FileHandler();
    }

    public void createMember(int memberID, String name, LocalDate birthday, String address, String email, boolean isOnCompetitionTeam, boolean isActive){
        db.createMember(memberID,name,birthday,address,email,isOnCompetitionTeam,isActive);
    }

    public void printMembers(){
        db.printMemberlist();
    }

    public ArrayList<Member> printYouthTeam(){
        return db.printYouthTeam();
    }

    public ArrayList<Member> printSeniorTeam(){
        return db.printSeniorTeam();
    }

    public int totalSubsription(){
        return db.totalSubscription();
    }

    public void saveMemberData(ArrayList<Member> members) {
        fh.saveMemberData(members, CSVPath);
    }

    public void upgradeMember(int memberID) {
        memberManager.upgradeMembers(memberID);
    }

}
