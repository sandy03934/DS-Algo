import java.util.Arrays;

/**
 * @author Sandip Singh.
 */
public class PermutationChecker {

    /**
     * Method1 is to sort both the strings and compare the value. This would be an algo
     * having time complexity O(nlogn) + O(n)
     * @param str1  String 1
     * @param str2  String 2
     * @return
     */
    public boolean method1(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }

        char [] s1 = str1.toCharArray();
        char [] s2 = str2.toCharArray();

        Arrays.sort(s1);
        Arrays.sort(s2);

        boolean flag = true;

        for (int i = 0; i < s1.length; i++) {
            if (s1[i] != s2[i]) {
                flag = false;
                break;
            }
        }

        return flag;
    }

    /**
     * Method2 is to sort both the strings and compare the value. This would be an algo
     * having time complexity O(n) with O(n) space complexity.
     * @param str1  String 1
     * @param str2  String 2
     * @return
     */
    public boolean method2(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }

        int [] chars = new int[128];

        for (int i = 0; i < str1.length(); i++) {
            chars[str1.charAt(i)] += 1;
        }

        boolean flag = true;

        for (int i = 0; i < str2.length(); i++) {
            chars[str2.charAt(i)] -= 1;
        }

        for (int i : chars) {
            if (i != 0) {
                flag = false;
                break;
            }
        }
        return flag;
    }
}
