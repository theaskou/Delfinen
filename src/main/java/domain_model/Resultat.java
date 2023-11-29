package domain_model;

import java.time.LocalDate;

public class Resultat {
    private int memberID;
    private String name;
    private LocalDate birthday;
    private Svømmediscipliner svømmediscipliner;
    private double bestTime;
    private String competition;
    private int rank;
    private LocalDate date;

    public Resultat(int memberID, String name, LocalDate birthday, Svømmediscipliner svømmediscipliner, double bestTime, LocalDate date){
        this.memberID = memberID;
        this.name = name;
        this.birthday = birthday;
        this.svømmediscipliner = svømmediscipliner;
        this.bestTime = bestTime;
        this.date = date;
    }

    public Resultat(int memberID, String name, LocalDate birthday, Svømmediscipliner svømmediscipliner, double bestTime, String competition, int rank, LocalDate date){
        this.memberID = memberID;
        this.name = name;
        this.birthday = birthday;
        this.svømmediscipliner = svømmediscipliner;
        this.bestTime = bestTime;
        this.competition = competition;
        this.rank = rank;
        this.date = date;
    }

    public String showResult(){
        String showResult = " ";
        return showResult;
    }

    @Override
    public String toString() {
        return "Resultat{" +
                "memberID=" + memberID +
                ", name='" + name + '\'' +
                ", svømmediscipliner=" + svømmediscipliner +
                ", bestTime=" + bestTime +
                ", competition='" + competition + '\'' +
                ", rank=" + rank +
                ", date=" + date + "\n" +
                '}';
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

    public Svømmediscipliner getSvømmediscipliner() {
        return svømmediscipliner;
    }

    public void setSvømmediscipliner(Svømmediscipliner svømmediscipliner) {
        this.svømmediscipliner = svømmediscipliner;
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


}
