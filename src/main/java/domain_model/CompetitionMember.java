package domain_model;

import java.time.LocalDate;

public class CompetitionMember extends Member {

    private double crawlBestTrainingTime;
    private double butterflyBestTrainingTime;
    private double breaststrokeBestTrainingTime;
    private double backstrokeBestTrainingTime;

    public CompetitionMember(int memberID, String name, LocalDate birthday, String address, String email, boolean isOnCompetitionTeam, boolean isActive, double crawlBestTrainingTime, double butterflyBestTrainingTime, double breaststrokeBestTrainingTime, double backstrokeBestTrainingTime){
        super(memberID, name, birthday, address, email, isOnCompetitionTeam, isActive);
        this.crawlBestTrainingTime = crawlBestTrainingTime;
        this.butterflyBestTrainingTime = butterflyBestTrainingTime;
        this.breaststrokeBestTrainingTime = breaststrokeBestTrainingTime;
        this.backstrokeBestTrainingTime = backstrokeBestTrainingTime;
    }

}
