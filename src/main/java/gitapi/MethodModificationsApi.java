package gitapi;

import com.github.javaparser.ast.body.ConstructorDeclaration;
import domain.FileVersion;
import domain.SourceElementModification;
import utils.AbstractSyntaxTreeReader;

import java.util.ArrayList;
import java.util.List;

public class MethodModificationsApi {

    public static List<SourceElementModification> getSourceElementModifications(String repositoryPath,
                                                                                String filePath) {
        List<FileVersion> fileVersions = FileVersionsApi.getDownloadedFileVersions(repositoryPath, filePath);
        return getSourceElementModifications(fileVersions);
    }

    private static List<SourceElementModification> getSourceElementModifications(List<FileVersion> fileVersions) {
        List<SourceElementModification> sourceElementModifications = new ArrayList<>();
        for (int currentVersionIndex = 0; currentVersionIndex < fileVersions.size() - 1; currentVersionIndex++) {
            int previousVersionIndex = currentVersionIndex + 1;
            FileVersion currentVersion = fileVersions.get(currentVersionIndex);
            FileVersion previousVersion = fileVersions.get(previousVersionIndex);
            sourceElementModifications.add(getSourceElementModification(currentVersion, previousVersion));
        }
        return sourceElementModifications;
    }

    private static SourceElementModification getSourceElementModification(FileVersion currentVersion,
                                                                          FileVersion previousVersion) {
        //System.out.println(currentVersion);
        //System.out.println(previousVersion);
        //List<ConstructorDeclaration> methods1 = AbstractSyntaxTreeReader.getAllConstructors(currentVersion.getFilePathInSavedDirectory());
        //List<ConstructorDeclaration> methods2 = AbstractSyntaxTreeReader.getAllConstructors(previousVersion.getFilePathInSavedDirectory());
        //methods1.forEach(System.out::println);
        return null;
    }

}
