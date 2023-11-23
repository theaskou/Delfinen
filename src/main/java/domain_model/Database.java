package domain_model;
import data_source.FileHandler;
import java.io.File;
import java.util.ArrayList;

public class Database {
    private FileHandler fh;
    private File CSVPath;
    private ArrayList<Member> members;

    public Database (File CSVPath) {
        this.CSVPath = CSVPath;
        this.fh = new FileHandler();
        this.members = fh.loadMemberData(CSVPath);

        if (CSVPath.exists()){
            this.members = fh.loadMemberData(CSVPath);
        } else {
            this.members = new ArrayList<>();
        }

    }

    public void createMember(int memberID, String name, int year, int month, int day, String address, String email, boolean isOnCompetitionTeam, boolean isActive){
        members.add(new Member(memberID, name, year, month, day, address, email, isOnCompetitionTeam, isActive));
        fh.saveListOfMemberData(members, CSVPath);

    }

    public void printMemberlist(){
        for (Member member : members){
            System.out.println(member);
        }
    }


}
