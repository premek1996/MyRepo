package processmetrics;

import domain.Commit;

import java.util.List;

/*
The number of previous modifications
without any comment message.
 */

public class CommitsWithoutMessageNumber {

    public static void calculate(List<Commit> commits) {
        long commitsWithoutMessageNumber = commits.stream()
                .map(Commit::getMessage)
                .filter(String::isEmpty)
                .count();
        System.out.println("Number of commits without message: " + commitsWithoutMessageNumber);
    }

}
