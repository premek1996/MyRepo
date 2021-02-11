package gitapi;


import domain.InvestigatedSourceElementModification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//git show --numstat --format= 114c412afbfba24ffb4fbc804e5308a823a16a78

public class InvestigatedSourceElementModificationApi {

    public static InvestigatedSourceElementModification getInvestigatedSourceElementModification
            (String repoPath, String filePath, String hash) {
        List<String> command = new ArrayList<>
                (Arrays.asList("git", "show", "--numstat", "--format=", hash));
        return new InvestigatedSourceElementModification("",
                "",
                5,
                10);
    }

}
