package com.preparation.arrays;

public class MedianOfSortedArrays {

    static int findMedianByMergeMethod(int [] a, int[] b) {
        int i = 0, j = 0;
        int m = a.length; int n = b.length;
        int[] res = new int[a.length + b.length];
        int count = 0;
        while (i <= m - 1 && j <= n - 1) {
            if (a[i] < b[j]) {
                res[count] = a[i];
                count++;
                i++;
            } else {
                res[count] = b[j];
                count++;
                j++;
            }
        }

        while (i <= m - 1) {
            res[count] = a[i];
            count++;
            i++;
        }

        while (j <= n - 1) {
            res[count] = b[j];
            count++;
            j++;
        }


        return (res[m] + res[m - 1]) / 2;
    }

    static int findMedian(int [] a, int [] b, int start_a, int end_a, int start_b, int end_b) {
        if (end_a - start_a == 1 && end_b - start_b == 1) {
            return (Math.max(a[start_a], b[start_b]) + Math.min(a[end_a], b[end_b])) / 2;
        }

        int m1 = (start_a + end_a) / 2;
        int m2 = (start_b + end_b) / 2;

        if (a[m1] == b[m2]) {
            return b[m2];
        }

        if (a[m1] < b[m2]) {
            start_a = m1;
            end_b = m2;
        } else if (a[m1] > b[m2]) {
            end_a = m1;
            start_b = m2;
        }

        return findMedian(a, b, start_a, end_a, start_b, end_b);
    }

    public static void main(String ... args) {
        int[] a = new int[] {11, 13, 20, 26, 30};
        int[] b = new int[] {10, 12, 17, 18, 35};

        int median = findMedianByMergeMethod(a, b);
        assert median == 17 : "Not Valid";

        median = findMedian(a, b, 0, a.length -1, 0, b.length -1);
        assert median == 17 : "Not Valid";
    }
}
