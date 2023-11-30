package domain_model.comparator;
import domain_model.Result;
import java.util.Comparator;


public class BreaststrokeComparator implements Comparator<Result> {

    public int compare(Result result1, Result result2){
        return Double.compare(result1.getBestTime(), result2.getBestTime());

    }



}
