package domain_model.comparator;
import domain_model.Result;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

public class JuniorComparator implements Comparator<Result> {
    private ArrayList<Result> junior;

    public JuniorComparator(ArrayList<Result> junior) {
        this.junior = junior;
    }
    @Override
    public int compare(Result junior1, Result junior2) {
        int result = junior1.getBirthday().compareTo(junior2.getBirthday());

        if (result == 0) {
            if (junior1.getBirthday().isAfter(LocalDate.of(0, 1, 1)) &&
                    junior2.getBirthday().isBefore(LocalDate.of(18, 0, 0))) {
                result = 1;
                junior.add(junior1);
                junior.add(junior2);
            }
        }

        return result;
    }

}
