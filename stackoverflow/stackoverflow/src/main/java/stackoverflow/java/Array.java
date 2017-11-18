package stackoverflow.java;

import java.util.Arrays;

/**
 * Created by sinsandi on 4/18/2017.
 */
public class Array {

    public static void main(String [] args) {
       int [] arr = new int[] {1, 2, 3, 4, 5};

       System.out.println(3 % arr.length);

       int [] targetArr = new int[arr.length];

//       for (int j = 0 ; j < arr.length; j++) {
//           targetArr[j] = arr[j];
//       }
       for (int i = 0 ; i < arr.length; i++) {
           targetArr[(i + 2) % arr.length] = arr[i];
       }

        System.out.println(Arrays.toString(targetArr));

       int sum = 0;
       for (int k : arr) {
           sum += k;
       }
        System.out.println(sum);

    }
}
