package domain_model;

import java.util.ArrayList;
import java.util.List;

public class MemberManager {

    private List<Member> members;

    public MemberManager() {
        this.members = new ArrayList<>();
    }

    public void upgradeMembers(int memberID) {
        for (int i = 0; i < members.size(); i++) {
            Member currentMember = members.get(i);

            if (currentMember.isOnCompetitionTeam()) {
                ArrayList<CompetitionMember> disciplineAndResult = new ArrayList<>();
                ArrayList<SvømmeDicipliner> discipline = new ArrayList<>();
                ArrayList<CompetitionMember> competitionAndRanking = new ArrayList<>();

                CompetitionMember competitionMember = new CompetitionMember(currentMember.getMemberID(),
                        currentMember.getName(), currentMember.getBirthday(), currentMember.getAddress(),
                        currentMember.getEmail(), currentMember.isOnCompetitionTeam(), currentMember.isActive(),
                        disciplineAndResult, discipline, competitionAndRanking);

                members.set(i, competitionMember);
            }
        }
    }
}
