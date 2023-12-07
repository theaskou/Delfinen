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

    public Result(int memberID, String name, LocalDate birthday, SwimmingDiscipline swimmingDiscipline, double bestTime, LocalDate date){
        this.memberID = memberID;
        this.name = name;
        this.birthday = birthday;
        this.swimmingDiscipline = swimmingDiscipline;
        this.bestTime = bestTime;
        this.date = date;
    }

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



    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

     public String getName() {
       return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday(){
        return birthday;
    }

    public SwimmingDiscipline getSwimmingDiscipline() {
        return swimmingDiscipline;
    }

    public void setSwimmingDiscipline(SwimmingDiscipline swimmingDiscipline) {
        this.swimmingDiscipline = swimmingDiscipline;
    }

    public double getBestTime() {
        return bestTime;
    }

    public void setBestTime(double bestTime) {
        this.bestTime = bestTime;
    }

    public String getCompetition() {
        return competition;
    }

    public void setCompetition(String competition) {
        this.competition = competition;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return name +
                ", Tid: " + bestTime + " Sekunder" +
                ", Dato: " + flipDateFormater(date.toString());
    }


}
