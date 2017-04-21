package practize.csvparser;

/**
 * @author Sandip Singh.
 */
public enum CSVFieldType {

    STRING {
        @Override
        public CSVField getType() {
            return new CSVStringField();
        }
    },

    URL {
        @Override
        public CSVField getType() {
            return new CSVURLField();
        }
    },

    EXPRESSION {
        @Override
        public CSVField getType() {
            return new CSVExpressionField();
        }
    };

    public abstract CSVField getType();
}
