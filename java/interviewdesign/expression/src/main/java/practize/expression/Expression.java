package practize.expression;

import java.util.Stack;

/**
 * @author Sandip Singh
 */
public class Expression {

    public static final char DELIM = ',';
    private String expression;

    public Expression(String expression) {
        this.expression = infixToPostFix(expression);
    }

    private String infixToPostFix(String expression) {
        StringBuilder builder = new StringBuilder();
        Stack<Character> operatorStack = new Stack<>();
        if (expression != null) {
            for (char c : expression.toCharArray()) {

                if (isNumber(c + "")) {
                    builder.append(c);
                } else if (c == '(') {
                    operatorStack.push(c);
                } else if (c == ')') {
                    char op;
                    while ((op = operatorStack.pop()) != '(') {
                        builder.append(DELIM);
                        builder.append(op);
                    }
                } else if (Operator.isOperator(c)) {
                    if (!operatorStack.isEmpty() && operatorStack.peek() != '(' && Operator.precedence(operatorStack.peek()) < Operator.precedence(c)) {
                        builder.append(DELIM);
                        builder.append(operatorStack.pop());
                    }
                     operatorStack.push(c);
                     builder.append(DELIM);
                    }
            }

            while (!operatorStack.isEmpty()) {
                builder.append(DELIM);
                builder.append(operatorStack.pop());
            }
        }
        return builder.toString();
    }

    private boolean isNumber(String c) {
        boolean flag = true;
        try {
            Integer.parseInt(c);
        } catch (NumberFormatException e) {
            flag = false;
        }
        return flag;
    }

    public int evaluate() {
        if (expression != null) {
            String [] arr = expression.split(",");
            Stack<String> operandStack = new Stack<>();
            for (String str : arr) {
                if (isNumber(str)) {
                    operandStack.push(str);
                } else if (Operator.isOperator(str.charAt(0))) {
                    int j = Integer.parseInt(operandStack.pop());
                    int i = Integer.parseInt(operandStack.pop());
                    operandStack.push(String.valueOf(Operator.resolve(i, j, str.charAt(0))));
                }
            }
            return Integer.parseInt(operandStack.pop());
        }
        return 0;
    }

    public String toString() {
        return this.expression;
    }

}
