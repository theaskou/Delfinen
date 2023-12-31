package domain_model;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import static user_interface.UserInterface.flipDateFormater;
import java.util.ArrayList;
import java.util.Random;

public class Member {

    private int memberID;
    private String name;
    private LocalDate birthday;
    private String address;
    private String email;
    private boolean isOnCompetitionTeam;
    private boolean isActive;
    private LocalDate subscriptionDate;



    public Member(int memberID , String name, LocalDate birthday, String address, String email, boolean isOnCompetitionTeam, boolean isActive, LocalDate subscriptionDate) {
        this.memberID = memberID;
        this.name = name;
        this.birthday = birthday;
        this.address = address;
        this.email = email;
        this.isOnCompetitionTeam = isOnCompetitionTeam;
        this.isActive = isActive;
        this.subscriptionDate = subscriptionDate;

    }

    // Getters and setters for attributes:
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

    public LocalDate getSubscriptionDate(){
        return subscriptionDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setOnCompetitionTeam(boolean onCompetitionTeam) {
        this.isOnCompetitionTeam = onCompetitionTeam;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }


    //Kontingent beregner
    public int calculateSubscription() {
        int subscription = 0; int juniorSub = 1000;
        int adultSub = 1600; int seniorSub = 1200;
        int passiveSub = 500;

        if (LocalDate.now().minusYears(18).isBefore(getBirthday()) && isActive() == true) {
            subscription += juniorSub;
        }
        if (LocalDate.now().minusYears(18).isAfter(getBirthday()) && LocalDate.now().minusYears(60).isBefore(getBirthday()) && isActive() == true) {
            subscription += adultSub;
        }
        if (LocalDate.now().minusYears(60).isAfter(getBirthday()) && isActive() == true) {
            subscription += seniorSub;
        }
        if (isActive() == false) {
            subscription += passiveSub;
        }

        return subscription;

    }


    //Formatters to return a String from boolean attribute:
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

    //toString for Member
    @Override
    public String toString() {
        return    "ID: " + memberID +
                ", Navn: " + name +
                ", Fødselsdag: " + flipDateFormater(birthday.toString()) +
                ", Addresse: " + address +
                ", Email: " + email +
                ", Konkurrencesvømmer: " + isOnCompetitionTeam(isOnCompetitionTeam) +
                ", Aktiv: " + isActive(isActive) +
                ", Dato for betalt kontingent: " + flipDateFormater(subscriptionDate.toString());
    }


}
