package practize.csvparser;

/**
 * @author Sandip Singh
 */
public class CSVStringField implements CSVField<String> {

    public String value(String v) {
         return v;
    }
}
