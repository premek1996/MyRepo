package domain;

public class InvestigatedSourceElementModification {

    private final String filePath;
    private final String previousFilePath;
    private final int addedLinesNumber;
    private final int deletedLinesNumber;

    public InvestigatedSourceElementModification(String filePath,
                                                 String previousFilePath,
                                                 int addedLinesNumber,
                                                 int deletedLinesNumber) {
        this.filePath = filePath;
        this.previousFilePath = previousFilePath;
        this.addedLinesNumber = addedLinesNumber;
        this.deletedLinesNumber = deletedLinesNumber;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getPreviousFilePath() {
        return previousFilePath;
    }

    public int getAddedLinesNumber() {
        return addedLinesNumber;
    }

    public int getDeletedLinesNumber() {
        return deletedLinesNumber;
    }

}
