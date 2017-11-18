package com.preparation.arrays;

public class MajorityElement {

    static int findMajorityElement(int[] arr) {
        // Iterate and decide the candidate.
        int count = 0;
        int candidate = -1;

        for(int i = 0; i < arr.length; i++) {
            if (count == 0) {
                candidate = arr[i];
                count = 1;
            } else {
                if (candidate == arr[i]) {
                    count++;
                } else {
                    count--;
                }
            }
        }


        if (count == 0) {
            return Integer.MIN_VALUE;
        }

        count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (candidate == arr[i]) {
                count++;
            }
        }

        return (count > arr.length / 2) ? candidate : Integer.MIN_VALUE;
    }
    public static void main(String ... args) {
        int [] numbers = new int[] {4, 7, 4, 7, 4, 7, 4, 7, 5};
        int majorityEl = findMajorityElement(numbers);

        assert Integer.MIN_VALUE == majorityEl : "Not Valid";

        numbers = new int[] {4, 7, 4, 7, 4, 7, 4, 7, 5, 7, 7};
        majorityEl = findMajorityElement(numbers);

        assert 7 == majorityEl : "Not Valid";
    }
}
