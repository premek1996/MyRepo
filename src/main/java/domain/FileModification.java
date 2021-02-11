package domain;

public class FileModification {

    private final String fileName;
    private final String previousFileName;
    private final int addedLinesNumber;
    private final int deletedLinesNumber;

    public FileModification(String fileName,
                            String previousFileName,
                            int addedLinesNumber,
                            int deletedLinesNumber) {
        this.fileName = fileName;
        this.previousFileName = previousFileName;
        this.addedLinesNumber = addedLinesNumber;
        this.deletedLinesNumber = deletedLinesNumber;
    }

    public String getFileName() {
        return fileName;
    }

    public String getPreviousFileName() {
        return previousFileName;
    }

    public int getAddedLinesNumber() {
        return addedLinesNumber;
    }

    public int getDeletedLinesNumber() {
        return deletedLinesNumber;
    }

}
