package domain_model;

import java.time.LocalDate;
import java.util.Random;

public class Member {


    private int memberID; // TODO: Skal kunne tildele et random nummer selv ved oprettelse.
    private String name;
    private LocalDate birthday;
    private String address;
    private String email;
    private boolean isOnCompetitionTeam;
    private boolean isActive;
//TODO skrive competitor attributter



    public Member(int memberID , String name, LocalDate birthday, String address, String email, boolean isOnCompetitionTeam, boolean isActive) {
        this.memberID = memberID;
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

    public String isOnCompetitionTeam(boolean isOnCompetitionTeam){
        if (isOnCompetitionTeam) {
            return "Ja";
        } else {
            return "Nej";
        }
    }

    public String isActive(boolean isActive) {
        if (isActive) {
            return "Ja";
        } else {
            return "Nej";
        }
    }

    @Override
    public String toString() {
        return "Medlem" +
                "MedlemsID: " + memberID +
                ", Navn: " + name + '\'' +
                ", Fødselsdag: " + birthday +
                ", Addresse: " + address + '\'' +
                ", Email: " + email + '\'' +
                ", Konkurrencesvømmer: " + isOnCompetitionTeam(isOnCompetitionTeam) +
                ", Aktiv: " + isActive(isActive);
    }
}
