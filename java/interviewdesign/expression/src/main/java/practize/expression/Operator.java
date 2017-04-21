package practize.expression;

/**
 * @author Sandip Singh
 */
public class Operator {

    public static int resolve(int i, int j, char operator) {
        int result = 0;
        switch (operator) {
            case '*' : result = (i * j);
                       break;
            case '/' : result = (i / j);
                       break;
            case '%' : result = (i % j);
                       break;
            case '+' : result = (i + j);
                       break;

            case '-' : result = (i - j);
                       break;
            default : throw new UnsupportedOperationException("Invalid Operator");
        }
        return result;
    }

    public static boolean isOperator(char c) {
        boolean flag = false;
        switch (c) {
                case '*' :
                case '/' :
                case '%' :
                case '+' :
                case '-' : flag = true;
                            break;
                default : throw new UnsupportedOperationException("Invalid Operator");
        }

        return flag;
    }

    public static int precedence(char c) {
        int precendence = -1;
        switch(c) {
            case '*' :
            case '/' :
            case '%' : precendence = 4;
                       break;
            case '+' :
            case '-' : precendence = 5;
                       break;
            default : throw new UnsupportedOperationException(c + " is Invalid Operator");
        }

        return precendence;
    }
}
