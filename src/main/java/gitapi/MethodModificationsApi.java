package gitapi;

import domain.FileVersion;
import domain.SourceElementModification;

import java.util.ArrayList;
import java.util.List;

public class MethodModificationsApi {

    public static List<SourceElementModification> getSourceElementModifications(String repositoryPath,
                                                                                String filePath) {
        List<FileVersion> fileVersions = FileVersionsApi.getDownloadedFileVersions(repositoryPath, filePath);
        fileVersions.forEach(System.out::println);
        return new ArrayList<>();
    }

}
