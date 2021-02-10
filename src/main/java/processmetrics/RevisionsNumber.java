package processmetrics;

import domain.Commit;

import java.util.List;

public class RevisionsNumber {

    public static void calculate(List<Commit> commits) {
        int revisionsNumber = commits.size();
        System.out.println("Number of revisions: "+revisionsNumber);
    }

}
