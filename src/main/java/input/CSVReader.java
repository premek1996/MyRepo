package input;

import gitapi.CommitBasicInfoApi;
import mapper.CSVInputRowMapper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CSVReader {

    private CSVReader() {
    }

    public static List<CSVInputRow> getRowsWithAvailableCommits(String csvFilePath) {
        List<CSVInputRow> rows = getRows(csvFilePath);
        return filterRowsWithAvailableCommits(rows);
    }

    private static List<CSVInputRow> getRows(String csvFilePath) {
        List<CSVInputRow> rows = new ArrayList<>();
        try (Reader in = new FileReader(csvFilePath)) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withHeader(CSVInputHeader.class)
                    .withSkipHeaderRecord()
                    .parse(in);
            records.forEach(record -> rows.add(CSVInputRowMapper.from(record)));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return rows;
    }

    private static List<CSVInputRow> filterRowsWithAvailableCommits(List<CSVInputRow> rows) {
        return rows.stream()
                .filter(CSVReader::hasAvailableCommit)
                .collect(Collectors.toList());
    }

    private static boolean hasAvailableCommit(CSVInputRow row) {
        return CommitBasicInfoApi.isCommitAvailable(row.getRepositoryPath(), row.getCurrentHashCommit());
    }

}
