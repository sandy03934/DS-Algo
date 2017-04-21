package practize.expression;

import org.junit.Test;

/**
 * Created by sinsandi on 4/20/2017.
 */
public class ExpressionTest {

    @Test
    public void testExpression1() {
//        System.out.println(new Expression("23+4/5-6"));
        Expression expression = new Expression("(23+7)/5-6");
        System.out.print(expression + " = ");
        System.out.println(expression.evaluate());

        expression = new Expression("4+20/5-6");
        System.out.print(expression + " = ");
        System.out.println(expression.evaluate());
    }

}