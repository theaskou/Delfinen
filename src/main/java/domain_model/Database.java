package domain_model;

import java.time.LocalDate;

import data_source.FileHandler;

import java.io.File;
import java.util.ArrayList;

public class Database {
    private ArrayList<Member> memberlist;
    private ArrayList<CompetitionMember> competitionMembersList;
    private FileHandler fh;
    private File CSVPath;
    private Member member;


    //TODO: Save member to file
    public Database(File CSVPath) {
        this.CSVPath = CSVPath;
        this.fh = new FileHandler();
        this.memberlist = fh.loadMemberData();

    }

    public void createMember(int memberID, String name, LocalDate birthday, String address, String email, boolean isOnCompetitionTeam, boolean isActive) {
        memberlist.add(new Member(memberID, name, birthday, address, email, isOnCompetitionTeam, isActive));
        fh.saveMemberData(memberlist, CSVPath);

    }

    public void createCompetionMember(int memberID, String name, LocalDate birthday,
                                      String address, String email, boolean isOnCompetitionTeam,
                                      boolean isActive) {
        memberlist.add(new CompetitionMember(memberID, name, birthday, address, email, isOnCompetitionTeam, isActive));
        fh.saveMemberData(memberlist, CSVPath);
    }

    public ArrayList<CompetitionMember> getCompetitionMember() {
        ArrayList<CompetitionMember> competitionMembers = new ArrayList<>();
        for (Member member : memberlist) {
            if (member instanceof CompetitionMember compMember) {
                competitionMembers.add(compMember);
            }

        }
        return competitionMembers;
    }

    public ArrayList<Member> memberlist() {
        return memberlist;
    }

    public ArrayList<Member> printYouthTeam() {
        ArrayList<Member> youthTeam = new ArrayList<>();
        for (Member member : memberlist) {
            if (LocalDate.now().minusYears(18).isBefore(member.getBirthday()) && member.isOnCompetitionTeam() == true) {
                youthTeam.add(member);
            }

        }
        return youthTeam;
    }

    public ArrayList<Member> printSeniorTeam() {
        ArrayList<Member> seniorTeam = new ArrayList<>();
        for (Member member : memberlist) {
            if (LocalDate.now().minusYears(18).isAfter(member.getBirthday()) && member.isOnCompetitionTeam() == true) {
                seniorTeam.add(member);
            }
        }
        return seniorTeam;
    }

    public void save() {
        fh.saveMemberData(memberlist, CSVPath);
    }

    public void printMemberlist() {
        for (Member member : memberlist) {
            System.out.println(member);
        }
    }

    public ArrayList<CompetitionMember> competitionMembersList() {
        return competitionMembersList;
    }

    public int totalSubscription() {
        int totalSubscription = 0;
        for (Member member : memberlist) {
            totalSubscription += member.calculateSubscription();
        }

        return totalSubscription;
    }

}