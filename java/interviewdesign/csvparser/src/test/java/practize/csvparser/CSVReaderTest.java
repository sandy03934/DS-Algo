package practize.csvparser;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Sandip Singh.
 */
public class CSVReaderTest {

    @Test
    public void testRead1() throws IOException {
        InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("test.csv");

        CSVReader reader = new CSVReader(stream);
        String str = reader.read();
        System.out.println(str);
    }

}