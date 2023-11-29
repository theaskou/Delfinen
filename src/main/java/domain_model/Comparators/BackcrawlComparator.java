package domain_model.Comparators;

import domain_model.Resultat;
import domain_model.Svømmediscipliner;

import java.util.Comparator;

public class BackcrawlComparator implements Comparator<Resultat> {

        public int compare(Resultat backCrawlResultat1, Resultat backCrawlResultat2){
            return   Double.compare(backCrawlResultat1.getBestTime(), backCrawlResultat2.getBestTime());
        }

}
