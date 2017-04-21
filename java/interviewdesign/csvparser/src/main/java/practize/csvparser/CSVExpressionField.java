package practize.csvparser;

import practize.expression.Expression;
/**
 * @author Sandip Singh.
 */
public class CSVExpressionField implements CSVField<String> {
    public String value(String v) {
        Expression expression = new Expression(v);
        return String.valueOf(expression.evaluate());
    }
}
