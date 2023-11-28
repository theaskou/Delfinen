package domain_model;

import java.util.Comparator;

public class ButterflyComparator implements Comparator<CompetitionMember> {

    public int compare(CompetitionMember competitionMember1, CompetitionMember competitionMember2){
        return   Double.compare(competitionMember1.getResults()[2], competitionMember2.getResults()[2]);
    }
}
