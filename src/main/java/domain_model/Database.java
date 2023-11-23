package domain_model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Database {
    private ArrayList<Member> members = new ArrayList<>();

    public void createMember(int memberID, String name, LocalDate birthday, String address, String email, boolean isOnCompetitionTeam, boolean isActive){
        members.add(new Member(memberID, name, birthday, address, email, isOnCompetitionTeam, isActive));
        //TODO: Save member to file
    }

    public ArrayList<Member> viewMembers(){
        return members;
    }
}
