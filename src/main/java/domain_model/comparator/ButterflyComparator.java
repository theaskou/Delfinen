package domain_model.comparator;
import domain_model.Result;
import java.util.Comparator;


public class ButterflyComparator implements Comparator<Result> {

    public int compare(Result butterflyResult1, Result butterflyResult2){
        return   Double.compare(butterflyResult1.getBestTime(), butterflyResult2.getBestTime());
    }


}
