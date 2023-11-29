package domain_model.Comparators;
import domain_model.Resultat;
import java.util.Comparator;


public class ButterflyComparator implements Comparator<Resultat> {

    public int compare(Resultat butterFlyResult1, Resultat butterFlyResult2){
        return   Double.compare(butterFlyResult1.getBestTime(), butterFlyResult2.getBestTime());
    }


}
