package domain_model;

import java.time.LocalDate;
import java.util.ArrayList;

public class CompetitionMember extends Member {

    private final String[] disciplines = {"Backcrawl", "Crawl", "Butterfly", "Breast Stroke"};
    private double[] results = new double[4];
    private LocalDate datoForResultat;


    public CompetitionMember(int memberID, String name, LocalDate birthday,
                             String address, String email, boolean isOnCompetitionTeam,
                             boolean isActive) {
        super(memberID, name, birthday, address, email, isOnCompetitionTeam, isActive);
    }

    //TODO Kan nok godt slettes
    public CompetitionMember(Member member){
        super(member.getMemberID(), member.getName(), member.getBirthday(),
                member.getAddress(), member.getEmail(), true,
                member.isActive());
    }




    // I UI tilføj en menu med numre over discipliner
    public void addResultToDiscipline(int discipline, double result, LocalDate dato){
            results[discipline - 1] = result;
            datoForResultat = dato;

    }

    //TODO lave dato, så den kun sætter dato på når der er tastet noget ind
    public String showResult(int i){
        String showResult = "";

            showResult += disciplines[i] + " : " + results[i] + " Sekunder, " + " Dato for resultat: " + datoForResultat + "\n";


        return showResult;
    }

}
