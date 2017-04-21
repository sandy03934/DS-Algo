package practize.csvparser;

import practize.csvparser.url.URLResolver;

import java.net.MalformedURLException;

/**
 * @author Sandip Singh.
 */
public class CSVURLField implements CSVField<String> {

    public String value(String v) {
        StringBuilder builder = new StringBuilder();
        try {
            URLResolver resolver = new URLResolver(v);
            builder.append(resolver.resolve());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}
