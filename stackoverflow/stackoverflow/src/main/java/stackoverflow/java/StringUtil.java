package stackoverflow.java;

public class StringUtil {

    public static void main(String ... args) {
        char[] str = "abc".toCharArray();
        for (int i = 0, j = str.length -1; i <= j; i++, j--) {
            int temp = str[i] ^ str[j];
            str[j] = (char) (temp ^ str[j]);
            str[i] = (char) (temp ^ str[i]);
        }

        System.out.println(String.valueOf(str));
    }
}
