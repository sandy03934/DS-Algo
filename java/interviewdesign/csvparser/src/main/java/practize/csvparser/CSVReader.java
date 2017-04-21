package practize.csvparser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author Sandip Singh.
 */
public class CSVReader {

    private InputStream stream;

    public CSVReader (InputStream inputStream) {
        this.stream = inputStream;
    }

    public String read() throws IOException {
        StringBuilder buffer = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            String line = reader.readLine();
            CSVParser parser = new CSVParser();
            CSVHeader header = parser.parseHeader(line);

            while ((line = reader.readLine()) != null) {
                buffer.append(parser.parse(line, header));
                buffer.append("\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return buffer.toString();
    }
}
