import java.util.Arrays;

/**
 * @author Sandip Singh.
 */
public class IsUnique {

    /**
     * Brute force way of finding if String has unique characters.
     * @param str  Source string
     * @return  true or false based on the string has a unique characters or not.
     */
    public boolean method1(String str) {

        if (str == null || str.trim().length() == 0) {
            return false;
        }
        boolean flag = true;

        char [] chars = str.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            for (int j = i + 1; j < chars.length; j++) {
                if (chars[i] == chars[j]) {
                    flag = false;
                    break;
                }
            }
        }

        return flag;
    }

    /**
     * Using extra space an associative array.
     * @param str
     * @return
     */
    public boolean method2(String str) {

        if (str == null || str.trim().length() == 0) {
            return false;
        }
        boolean flag = true;

        char [] chars = str.toCharArray();
        char [] count = new char[256];

        for (int i = 0; i < chars.length; i++) {
            count[chars[i]] += 1;
        }

        for (char c : chars) {
            if (count[c] > 1) {
                flag = false;
                break;
            }
        }

        return flag;
    }


    /**
     * Sort the string and find if the two consecutive characters are same. Runtime complexity O(N log N).
     * @param str source string.
     * @return  true or false based if the string has unique character.
     */
    public boolean method3(String str) {

        if (str == null || str.trim().length() == 0) {
            return false;
        }
        boolean flag = true;

        char [] chars = str.toCharArray();

        Arrays.sort(chars);

        for (int i = 0; i < chars.length - 1; i++) {
            if (chars[i] == chars[i+1]) {
                flag = false;
                break;
            }
        }

        return flag;
    }

    /**
     * Sort the string and find if the two consecutive characters are same. Runtime complexity O(N log N).
     * @param str source string.
     * @return  true or false based if the string has unique character.
     */
    public boolean method4(String str) {

        if (str == null || str.trim().length() == 0) {
            return false;
        }
        int checker = 0;

        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i) - 'a';

            if ((checker & (1 << val)) > 0) {
                return false;
            }
            checker |= (1 << val);
        }
        return true;
    }

}
