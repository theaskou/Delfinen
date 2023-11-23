package domain_model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {

    private Database db;

    public Controller(){
        db = new Database();
    }

    public void createMember(int memberID, String name, LocalDate birthday, String address, String email, boolean isOnCompetitionTeam, boolean isActive){
        db.createMember(memberID,name,birthday,address,email,isOnCompetitionTeam,isActive);
    }

    public ArrayList<Member> viewMembers(){
       return db.viewMembers();
    }



}
