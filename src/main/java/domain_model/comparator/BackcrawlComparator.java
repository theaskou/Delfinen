package domain_model.comparator;

import domain_model.Result;

import java.util.Comparator;

public class BackcrawlComparator implements Comparator<Result> {

        public int compare(Result backcrawlResult1, Result backcrawlResult2){
            return   Double.compare(backcrawlResult1.getBestTime(), backcrawlResult2.getBestTime());
        }

}
