package input;

import gitapi.CommitBasicInfoApi;
import mapper.CSVInputRowMapper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CSVReader {

    private static final String DEFAULT_OUTPUT_REPOSITORY_NAME = "unknown-repository";
    private static final String DEFAULT_OUTPUT_REPOSITORY_DIR = System.getProperty("user.home") + File.separator + "java-metrics-source-repos";
    private static final Pattern PATTERN = Pattern.compile(".*:(.*)\\.git");

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
        return CommitBasicInfoApi.isCommitAvailable(getRepositoryPath(row.getRepositoryURI()), row.getCurrentHashCommit());
    }

    private static String getRepositoryPath(String repositoryURI) {
        return DEFAULT_OUTPUT_REPOSITORY_DIR + "\\" + getRepositoryName(repositoryURI);
    }

    private static String getRepositoryName(String repositoryURI) {
        return Optional.of(PATTERN.matcher(repositoryURI))
                .filter(Matcher::find)
                .map(matcher -> matcher.group(1))
                .orElse(DEFAULT_OUTPUT_REPOSITORY_NAME);
    }

}
