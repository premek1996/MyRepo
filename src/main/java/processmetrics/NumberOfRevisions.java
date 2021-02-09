package processmetrics;

import domain.Commit;

import java.util.List;

public class NumberOfRevisions {

    public static void calculate(List<Commit> commits) {
        int numberOfRevisions = commits.size();
        System.out.println("Number of revisions: "+numberOfRevisions);
    }

}
