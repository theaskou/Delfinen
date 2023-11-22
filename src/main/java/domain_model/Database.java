package domain_model;

import java.util.ArrayList;

public class Database {
    private ArrayList<Member> members;

    public void createMember(int memberID, String name, int year, int month, int day, String address, String email, boolean isOnCompetitionTeam, boolean isActive){
        members.add(new Member(memberID, name, year, month, day, address, email, isOnCompetitionTeam, isActive));
        //TODO: Save member to file
    }
}
