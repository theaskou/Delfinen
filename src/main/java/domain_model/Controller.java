package domain_model;

import data_source.FileHandler;

import java.io.File;
import java.util.ArrayList;

public class Controller {
    Database database;
    FileHandler fileHandler = new FileHandler();
    File CSVPath = new File("memberData.csv");
    ArrayList<Member> members = new ArrayList<>();


    public void createMember(int memberID, String name, int year, int month, int day, String address, String email, boolean isOnCompetitionTeam, boolean isActive){
        Member member = new Member(memberID, name, year, month, day, address, email, isOnCompetitionTeam, isActive);
        members.add(member);
    }

    public void saveListOfMembersToCSV(ArrayList<Member> members) {
        fileHandler.saveListOfMemberData(members, CSVPath);
    }

    public void printMemberlist() {
       database.printMemberlist();
    }

}
