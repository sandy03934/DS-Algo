package com.preparation.arrays;

public class FindPivotInRotatedArray {

    static int findPivotLinearWay(int [] a) {
        int pivot = 0;
        int start = 0;
        int end = a.length - 1;

        if (a[start] < a[end]) {
            return pivot;
        }

        for (int i = 0; i < end; i++) {
            if (a[i] > a[i + 1]) {
                pivot = i + 1;
                break;
            }
        }

        return a[pivot];
    }

    static int findPivotBinarySearchWay(int [] a) {
        int pivot = 0;
        int start = 0;
        int end = a.length - 1;

        if (a[start] < a[end]) {
            return pivot;
        }

        while (start <= end) {
            int mid = (start + end) / 2;
            if (a[mid] > a[mid + 1]) {
                pivot = mid + 1;
                break;
            } else if (a[start] > a[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return a[pivot];
    }

    public static void main(String ... args) {
        int [] numbers = new int[] {73, 85, 94, 21, 27, 34, 47, 54, 66};
        int pivot = findPivotLinearWay(numbers);

        assert pivot == 21 : "Not Valid";

        pivot = findPivotBinarySearchWay(numbers);

        assert pivot == 21 : "Not Valid";
    }
}
