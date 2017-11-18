package com.preparation.arrays;

public class MinimumLengthSubArrayWithSum {

    static int findMinimumLengthSubArray(int sum, int[] arr) {
        int startPos = -1;
        int endPos = -1;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < arr.length; i++) {  //Iterate on the array keeping two pointers and capturing the minimum length
            int currentSum = 0;
            for (int j=i ; j < arr.length && (j - i + 1) < min; j++) {
                currentSum += arr[j];
                if (currentSum == sum) {
                    startPos = i;
                    endPos = j;
                    min = endPos - startPos + 1;
                }
            }
        }
        System.out.println("Start Pos " + startPos + " endPos " + endPos + " Min " + min);
        return min;
    }

    public static void main (String ... args) {
        int [] numbers = new int[] {2, 3, 1, 1, -1, 3, 5};
        int sum = 7;

        int min = findMinimumLengthSubArray(sum, numbers);

        assert 2 == min : "Not Valid ";
     }
}
