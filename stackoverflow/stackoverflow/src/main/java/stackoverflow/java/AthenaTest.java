package stackoverflow.java;

/**
 * Created by sinsandi on 4/3/2017.
 */
public class AthenaTest {


    public static void main(String [] args) {
//        String abc = "He had:Abc;Twice,Hello    Hii-hu.per";
//
//        String [] arr = abc.split("[\\s\\t;,.\\-]");
//
//        System.out.println(Arrays.toString(arr));

        System.out.print(maxMoney(7, 10));
    }


    static int maxMoney(int n, long k) {
        long sum = 0;
        for (long i = 1; i <= n; i++) {
            if ((sum + i) == k) {
                System.out.println(sum);
                sum = (sum + i) - 1;
            } else {
                sum = sum + i;
            }
        }

        return (int)(sum % (Math.pow(10,9) + 7));

    }
}
