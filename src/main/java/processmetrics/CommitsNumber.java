package processmetrics;

import domain.Commit;

import java.util.List;

/*
The metric represents the number of commits of a given
Java class/method during development of the investigated
release of a software system.
 */

public class CommitsNumber {

    public static void calculate(List<Commit> commits) {
        int commitsNumber = commits.size();
        System.out.println("Number of commits: "+commitsNumber);
    }

}
