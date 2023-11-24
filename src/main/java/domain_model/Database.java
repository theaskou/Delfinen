package domain_model;

import java.time.LocalDate;
import data_source.FileHandler;
import java.io.File;
import java.util.ArrayList;

public class Database {
    private ArrayList<Member> memberlist;
    private FileHandler fh;
    private File CSVPath;

        //TODO: Save member to file
    public Database (File CSVPath) {
        this.CSVPath = CSVPath;
        this.fh = new FileHandler();
        this.memberlist = fh.loadMemberData();

    }

    public void createMember(int memberID, String name, LocalDate birthday, String address, String email, boolean isOnCompetitionTeam, boolean isActive){
        memberlist.add(new Member(memberID, name, birthday, address, email, isOnCompetitionTeam, isActive));
        fh.saveMemberData(memberlist, CSVPath);

    }

    public ArrayList<Member> printYouthTeam(){
        ArrayList<Member> youthTeam = new ArrayList<>();
        for (Member member: memberlist) {
            if (LocalDate.now().minusYears(18).isBefore(member.getBirthday()) && member.isOnCompetitionTeam() == true){
                youthTeam.add(member);
            }

        }
        return youthTeam;
    }

    public ArrayList<Member> printSeniorTeam(){
        ArrayList<Member> seniorTeam = new ArrayList<>();
        for (Member member : memberlist){
            if (LocalDate.now().minusYears(18).isAfter(member.getBirthday()) && member.isOnCompetitionTeam() == true){
                seniorTeam.add(member);
            }
        }
        return seniorTeam;
    }

    public int totalSubscription(){
        int subscription = 0;
        for (Member member : memberlist){
            if (LocalDate.now().minusYears(18).isBefore(member.getBirthday()) && member.isActive() == true){
                subscription += 1000;
            }
            if (LocalDate.now().minusYears(18).isAfter(member.getBirthday()) && LocalDate.now().minusYears(60).isBefore(member.getBirthday()) && member.isActive() == true){
                subscription += 1600;
            }
            if (LocalDate.now().minusYears(60).isAfter(member.getBirthday()) && member.isActive() == true){
                subscription += 1600*0.75;
            }
            if (member.isActive() == false){
                subscription += 500;
            }

        }
        return subscription;
    }

    public void printMemberlist(){
        for (Member member : memberlist){
            System.out.println(member);
        }
    }

}
