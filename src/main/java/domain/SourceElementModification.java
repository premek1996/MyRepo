package domain;

public class SourceElementModification {

    private final String hash;
    private final int addedLines;
    private final int deletedLines;

    public SourceElementModification(String hash,
                                     int addedLines,
                                     int deletedLines) {
        this.hash = hash;
        this.addedLines = addedLines;
        this.deletedLines = deletedLines;
    }

    public String getHash() {
        return hash;
    }

    public int getAddedLines() {
        return addedLines;
    }

    public int getDeletedLines() {
        return deletedLines;
    }

}
