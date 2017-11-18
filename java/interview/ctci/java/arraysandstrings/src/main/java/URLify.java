/**
 * @author Sandip Singh.
 */
public class URLify {

    public String method1(String str) {

        char [] arr = str.toCharArray();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == ' ') {
                insertUrlEncoding(i, arr);
            }
        }

        return new String(arr);

    }

    private void insertUrlEncoding(int pos, char[] arr) {

        char [] urlencoding = new char[] {'%', '2', '0'};

        int i = arr.length - 1;
        int j = urlencoding.length - 1;
        while (i >= pos) {
            if (i != pos + 2) {
                arr[i] = arr[i-2];
            } else {
                arr[i] = urlencoding[j];
                j--;
            }
            i--;
        }

    }
}
