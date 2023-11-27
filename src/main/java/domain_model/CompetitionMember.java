package domain_model;

import java.time.LocalDate;
import java.util.ArrayList;

public class CompetitionMember extends Member {

    private double crawlBestTrainingTime;
    private double butterflyBestTrainingTime;
    private double breaststrokeBestTrainingTime;
    private double backstrokeBestTrainingTime;

    private ArrayList<CompetitionMember> diciplinAndResult;
    private ArrayList<SvømmeDicipliner> diciplin;
    private ArrayList<CompetitionMember> competitionAndRanking;

    public CompetitionMember(int memberID, String name, LocalDate birthday, String address, String email, boolean isOnCompetitionTeam, boolean isActive, ArrayList diciplinAndResult, ArrayList<SvømmeDicipliner> diciplin, ArrayList competitionAndRanking){
        super(memberID, name, birthday, address, email, isOnCompetitionTeam, isActive);
        this.diciplinAndResult = diciplinAndResult;
        this.diciplin = diciplin;
        this.competitionAndRanking = competitionAndRanking;

/*      this.crawlBestTrainingTime = crawlBestTrainingTime;
        this.butterflyBestTrainingTime = butterflyBestTrainingTime;
        this.breaststrokeBestTrainingTime = breaststrokeBestTrainingTime;
        this.backstrokeBestTrainingTime = backstrokeBestTrainingTime;*/
    }

}
