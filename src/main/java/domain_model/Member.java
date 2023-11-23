package domain_model;

import java.time.LocalDate;

public class Member {

    private int memberID; // TODO: Skal kunne tildele et random nummer selv ved oprettelse.
    private String name;
    private LocalDate birthday;
    private String address;
    private String email;
    private boolean isOnCompetitionTeam;
    private boolean isActive;
//TODO skrive competitor attributter



    public Member(int memberID, String name, int year, int month, int day, String address, String email, boolean isOnCompetitionTeam, boolean isActive) {
        this.memberID = memberID; //Random heltal
        this.name = name;
        this.birthday = birthday;
        this.address = address;
        this.email = email;
        this.isOnCompetitionTeam = isOnCompetitionTeam;
        this.isActive = isActive;

    }
    public int getMemberID(){
        return memberID;
    }

    public String getName(){
        return name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getAddress(){
        return address;
    }
    public String getEmail(){
        return email;
    }
    public boolean isOnCompetitionTeam(){
        return isOnCompetitionTeam;
    }
    public boolean isActive(){
        return isActive;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setOnCompetitionTeam(boolean onCompetitionTeam) {
        isOnCompetitionTeam = onCompetitionTeam;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
