package com.preparation.arrays;

public class BinarySearch {

    static int binarySearch(int [] numbers, int num) {
        int start = 0;
        int end = numbers.length - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (numbers[mid] == num) {
                return mid;
            }

            if (numbers[mid] > num) {
                end = mid - 1;
            }

            if (numbers[mid] < num) {
                start = mid + 1;
            }
        }

        return -1;
    }

    public static void main(String ... args) {
        int [] numbers = new int[] {1, 5, 6, 8, 14, 20, 28, 39, 45, 50, 55};

        int searchIndex = binarySearch(numbers, 14);

        assert searchIndex == 4 : "Not Valid";

        searchIndex = binarySearch(numbers, 100);

        assert searchIndex == -1 : "Not Valid";
    }
}
