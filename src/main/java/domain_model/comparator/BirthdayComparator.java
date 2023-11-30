package domain_model.comparator;

import domain_model.Result;

import javax.swing.text.TabableView;
import java.time.LocalDate;
import java.util.Comparator;

public class BirthdayComparator implements Comparator<Result> {
    public int compare(Result resultBirthday1, Result resultBirthday2){
        return resultBirthday1.getBirthday().compareTo(resultBirthday2.getBirthday());
    }



    // ved ikke om man kan bruge dette:
    // if (result.getBirthday().isBefore(LocalDate.now().minusYears(18)))
    // resultBirthday1.getBirthday().compareTo(resultBirthday2.getBirthday());
}
