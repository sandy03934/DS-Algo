package practize.helloworld;

import java.util.StringJoiner;

/**
 * Created by sinsandi on 10/10/2016.
 */
public class RotateArray {

    public static void main(String[] args) {
        int [] a = new int [] {1, 2, 3, 4, 5, 6};
        int d = 4;
        int [] k = new int[a.length];
        StringJoiner joiner = new StringJoiner(" ");
        for (int i = 0; i < a.length; i ++) {
            System.out.println((i + (a.length - d)) % a.length  + "  " + i);
            k [(i + (a.length - d)) % a.length] = a [i];
        }

        for (int i = 0; i < k .length; i++) {
            joiner.add("" + k[i]);
        }
        System.out.println(joiner.toString());

        System.out.println(0 % 2);

     }
}
