package practize.csvparser;

import java.util.StringTokenizer;

/**
 * @author Sandip Singh
 */
public class CSVParser {

    private static final String DELIM = ",";

    public String parse(String csv, CSVHeader header) {
        return parse(csv, DELIM, header);
    }

    public String parse(String csv, String delim, CSVHeader header) {
        StringBuilder builder = new StringBuilder();

        StringTokenizer tokenizer = new StringTokenizer(csv, delim);
        int index = 0;
        while (tokenizer.hasMoreTokens()) {
            String nextToken = tokenizer.nextToken();
            CSVFieldType fieldType = header.get(index);
            builder.append(fieldType.getType().value(nextToken));
            builder.append(",");
            index++;
        }
        return builder.toString();
    }

    public CSVHeader parseHeader(String csv) {
        return parseHeader(csv, DELIM);
    }

    private CSVHeader parseHeader(String csv, String delim) {
        CSVHeader header = new CSVHeader();
        StringTokenizer tokenizer = new StringTokenizer(csv, delim);

        while (tokenizer.hasMoreTokens()) {
            String nextToken = tokenizer.nextToken();
            header.add(CSVFieldType.valueOf(nextToken.toUpperCase()));
        }

        return header;
    }
}
