package processmetrics;

import domain.Commit;

import java.util.List;

public class CommitsNumber {

    public static void calculate(List<Commit> commits) {
        int commitsNumber = commits.size();
        System.out.println("Number of commits: "+commitsNumber);
    }

}
