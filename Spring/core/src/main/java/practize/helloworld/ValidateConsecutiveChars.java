package practize.helloworld;

import java.util.Stack;

/**
 * Created by sinsandi on 10/11/2016.
 */
public class ValidateConsecutiveChars {

    public static void main(String[] args) {
        char [] str = "ABAB".toCharArray();
        boolean matched = true;
        Stack<Character> stack = new Stack<Character>();

        boolean consecutive = false;
        char prev = '\0';
        for (int k = 0; k < str.length; k++) {
            if(prev == '\0') {
                prev = str[k];
            } else if (prev == str[k]){
                consecutive = true;
                prev = str[k];
            } else if (prev != str[k] && consecutive) {
                consecutive = false;
                prev = str[k];
            } else {
                break;
            }

        }

//        if (!stack.empty() && stack.peek() == str[k]) {
//            stack.pop();
//        } else {
//            stack.push(str[k]);
//        }
//        if (!stack.isEmpty()) {
//            matched = false;
//        }

//        if (str[k] != str[k + 1] && matched) {
//            matched = false;
//        } else if (str[k] == str[k + 1]) {
//            matched = true;
//        }

       System.out.println(consecutive);
        try {
            testMethod();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void testMethod() throws Exception {
        try {
            throw new Exception();
        } catch (Exception e) {
            System.out.println("Exception Caught");
            throw new Exception();
        } finally {
            System.out.println("Finnally Excuted");
        }
    }
}
