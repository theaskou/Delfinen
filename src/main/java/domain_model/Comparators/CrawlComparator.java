package domain_model.Comparators;

import domain_model.Resultat;
import domain_model.Svømmediscipliner;

import java.util.Comparator;

public class CrawlComparator implements Comparator<Resultat> {

    public int compare(Resultat disciplinCrawl1, Resultat discoplinCrawl2){
        return   Double.compare(disciplinCrawl1.getBestTime(),discoplinCrawl2.getBestTime());
    }
}
