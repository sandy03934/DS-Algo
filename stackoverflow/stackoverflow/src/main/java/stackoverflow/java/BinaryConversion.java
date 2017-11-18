package stackoverflow.java;

/**
 * Created by sinsandi on 4/18/2017.
 */
public class BinaryConversion {

    public static void main(String [] args) {
        System.out.println(printBinary(328));
    }

    private static int printBinary(int num) {
        int maxZeros = 0;
        int currZero = -1;
        while (num > 0) {
            if (currZero != -1 && num % 2 == 0) {
                currZero++;
            } else if (num % 2 == 1) {
                if (currZero != -1 && maxZeros < currZero) {
                    maxZeros = currZero;
                }
                currZero = 0;
            }
            System.out.print(num % 2);
            num = num >> 1;
        }

        return maxZeros;
    }
}
