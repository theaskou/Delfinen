package domain_model;

import static user_interface.UserInterface.flipDateFormater;
import java.time.LocalDate;

public class Result {
    private int memberID;
    private String name;
    private LocalDate birthday;
    private SwimmingDiscipline swimmingDiscipline;
    private double bestTime;
    private String competition;
    private int rank;
    private LocalDate date;

    //Konstruktør for trænings resultat
    public Result(int memberID, String name, LocalDate birthday, SwimmingDiscipline swimmingDiscipline, double bestTime, LocalDate date){
        this.memberID = memberID;
        this.name = name;
        this.birthday = birthday;
        this.swimmingDiscipline = swimmingDiscipline;
        this.bestTime = bestTime;
        this.date = date;
    }

    //Konstruktør for stævne resultat
    public Result(int memberID, String name, LocalDate birthday, SwimmingDiscipline swimmingDiscipline, double bestTime, String competition, int rank, LocalDate date){
        this.memberID = memberID;
        this.name = name;
        this.birthday = birthday;
        this.swimmingDiscipline = swimmingDiscipline;
        this.bestTime = bestTime;
        this.competition = competition;
        this.rank = rank;
        this.date = date;
    }

    //Getters and setters for Result
    public int getMemberID() {
        return memberID;
    }

     public String getName() {
       return name;
    }

    public LocalDate getBirthday(){
        return birthday;
    }

    public SwimmingDiscipline getSwimmingDiscipline() {
        return swimmingDiscipline;
    }

    public double getBestTime() {
        return bestTime;
    }

    public String getCompetition() {
        return competition;
    }

    public int getRank() {
        return rank;
    }

    public LocalDate getDate() {
        return date;
    }
    
    //toString for Result
    @Override
    public String toString() {
        return name +
                ", Tid: " + bestTime + " Sekunder" +
                ", Dato: " + flipDateFormater(date.toString());
    }


}
