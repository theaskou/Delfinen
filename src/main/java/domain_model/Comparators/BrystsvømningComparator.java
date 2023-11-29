package domain_model.Comparators;
import domain_model.Resultat;
import java.util.Comparator;


public class BrystsvømningComparator implements Comparator<Resultat> {

    public int compare(Resultat resultat1, Resultat resultat2){
        return Double.compare(resultat1.getBestTime(), resultat2.getBestTime());

    }



}
