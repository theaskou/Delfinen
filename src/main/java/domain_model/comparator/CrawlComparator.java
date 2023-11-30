package domain_model.comparator;

import domain_model.Result;

import java.util.Comparator;

public class CrawlComparator implements Comparator<Result> {

    public int compare(Result crawlResult1, Result crawlResult2){
        return   Double.compare(crawlResult1.getBestTime(),crawlResult2.getBestTime());
    }
}
