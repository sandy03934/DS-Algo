package com.preparation.arrays;

public class PeakElement {


    static int findPeakElementLinearMethod(int []  arr) {
        if (arr == null) {
            return Integer.MIN_VALUE;
        }

        if (arr.length == 1) {
            return arr[0];
        }

        if (arr.length == 2) {
            return arr[0] > arr[1] ? arr[0] : arr[1];
        }

        int i = 0;
        int peakElement = i;

        do {
            if ((i == 0 || arr[i] >= arr[i - 1]) && (i == arr.length -1 || arr[i] >= arr[i + 1])) {
                peakElement = i;
                break;
            }
            i++;
        } while (i <= arr.length -1);

        return arr[peakElement];
    }

    static int findPeakElementBinarySearch(int [] arr) {
        if (arr == null) {
            return Integer.MIN_VALUE;
        }

        if (arr.length == 1) {
            return arr[0];
        }

        if (arr.length == 2) {
            return arr[0] > arr[1] ? arr[0] : arr[1];
        }

        int i = 0;
        int j = arr.length - 1;

        int peakElement = Integer.MIN_VALUE;
        while (i <= j) {
            int mid = (i + j) / 2;

            if ((mid == 0 || arr[mid - 1] <= arr[mid]) && (mid == arr.length - 1 || arr[mid] >= arr[mid + 1])) {
                peakElement = arr[mid];
                break;
            } else if (mid > 0 && arr[mid - 1] > arr[mid]) {
                j = mid - 1;
            } else {
                i = mid + 1;
            }
        }
        return peakElement;
    }

    public static void main(String [] args) {
        int [] numbers = new int[] {39, 38, 14, 23, 45, 43};
        int peakVal = findPeakElementLinearMethod(numbers);
        assert 39 == peakVal : "Not Valid!!";
        peakVal = findPeakElementBinarySearch(numbers);
        assert 39 == peakVal;

        numbers = new int[] {37, 38, 14, 23, 45, 43};
        peakVal = findPeakElementLinearMethod(numbers);
        assert peakVal == 38 : "Not Valid!!";
        peakVal = findPeakElementBinarySearch(numbers);
        assert peakVal == 38 : "Not Valid!!";

        numbers = new int[] {37, 38, 39, 40, 45, 43};
        peakVal = findPeakElementLinearMethod(numbers);
        assert peakVal == 45 : "Not Valid!!";
        peakVal = findPeakElementBinarySearch(numbers);
        assert peakVal == 45 : "Not Valid!!";

        numbers = new int[] {37, 38, 39, 40, 41, 43};
        peakVal = findPeakElementLinearMethod(numbers);
        assert peakVal == 43 : "Not Valid!!";
        peakVal = findPeakElementBinarySearch(numbers);
        assert peakVal == 43 : "Not Valid!!";
    }
}
