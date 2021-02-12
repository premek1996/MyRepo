package domain;

public class SourceElementModification {

    private final String filePath;
    private final String previousFilePath;
    private final int addedLines;
    private final int deletedLines;

    public SourceElementModification(String filePath,
                                     String previousFilePath,
                                     int addedLines,
                                     int deletedLines) {
        this.filePath = filePath;
        this.previousFilePath = previousFilePath;
        this.addedLines = addedLines;
        this.deletedLines = deletedLines;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getPreviousFilePath() {
        return previousFilePath;
    }

    public int getAddedLines() {
        return addedLines;
    }

    public int getDeletedLines() {
        return deletedLines;
    }

}
