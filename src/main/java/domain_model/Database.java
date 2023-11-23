package domain_model;

import java.time.LocalDate;
import data_source.FileHandler;
import java.io.File;
import java.util.ArrayList;

public class Database {
    private ArrayList<Member> members;
    private FileHandler fh;
    private File CSVPath;

        //TODO: Save member to file
    public Database (File CSVPath) {
        this.CSVPath = CSVPath;
        this.fh = new FileHandler();
        this.members = fh.loadMemberData();

    }

    public void createMember(int memberID, String name, LocalDate birthday, String address, String email, boolean isOnCompetitionTeam, boolean isActive){
        members.add(new Member(memberID, name, birthday, address, email, isOnCompetitionTeam, isActive));
        fh.saveMemberData(members, CSVPath);

    }

    public void printMemberlist(){
        for (Member member : members){
            System.out.println(member);
        }
    }


}
