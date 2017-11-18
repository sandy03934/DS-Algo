package stackoverflow.java;

import java.util.Arrays;

/**
 * Created by sinsandi on 3/25/2017.
 */
public class Test {

    public static void main(String [] args) {
//        System.out.println(intToString(1));
//        System.out.println(intToString(5567));
//
//        System.out.println(sumOfAllDigits(567));
//
//        System.out.println(reverseNumber(12345));
//        System.out.println(reverseNumber2(12345));
//        System.out.println(fibonacci(7));

        System.out.println(findNextHighestNumber(12345));

        System.out.println(findNextHighestNumber(43821));

        System.out.println(findNextLowerNumber(5461));

        System.out.println(findNextLowerNumber(43821));
    }

    private static String intToString(int num) {
        char [] str = new char[(int) Math.log10(num) + 1];
        for (int len = str.length - 1; len >= 0; len--) {
            int d = num % 10;
            num = num / 10;
            str[len] = (char) (d +  48);
        }

        return new String(str);
    }

    private static long sumOfAllDigits(int num) {
        int digits = (int) Math.log10(num) + 1;
        long sum = 0;
        for (digits = digits - 1; digits >= 0 ; digits--) {
            int d = num % 10;
            sum += d;
            num = num / 10;
        }
        return sum;
    }

    private static int reverseNumber(int num) {
        int digits = findDigitsInCurrentNumber(num);
        int sum = 0;

        while (num != 0) {
            int d = num % 10;
            digits--;
            sum += d * Math.pow(10 , digits);
            num = num / 10;
        }
        return sum;
    }

    private static int reverseNumber2(int num) {
        int reverse = 0;

        while (num != 0) {
            reverse = reverse * 10;
            reverse = reverse + num%10;
            num = num/10;
        }
        return reverse;
    }


    private static int findDigitsInCurrentNumber(int num) {
        int count = 0;
        if (num < 0) {
            num = ~num;
        }

        while(num != 0) {
            count++;
            num = num/10;
        }
        return count;
    }

    public static int fibonacci(int n) {
        int a = 0;
        int b = 1;

        if (n == 2) {
            return 1;
        }
        for (int i = 2 ; i <= n; i++) {
            int c = a + b;
            a  = b;
            b = c;
        }
        return a;
    }

    public static int findNextHighestNumber(int number) {

        int [] numbers = toIntegerArray(number);
        int pos = -1;
        for (int i = numbers.length - 1; i > 0 ; i--) {
            if (numbers[i] > numbers[i - 1]) {
                pos = i - 1;
                break;
            }
        }

        if (pos != -1) {
            int posNextBigNumber = findNextBigNumber(pos, numbers);
            numbers = swap(pos, posNextBigNumber, numbers);
            numbers = sortArrayFromPos(pos + 1, numbers, true);
            System.out.println(Arrays.toString(numbers));
            return toInteger(numbers);
        } else {
            return number;
        }
    }

    public static int findNextLowerNumber(int number) {
        int [] numbers = toIntegerArray(number);

        int pos = -1;

        for (int i = numbers.length - 1; i > 0; i--) {
            if (numbers[i] < numbers[i-1]) {
                pos = i - 1;
                break;
            }
        }

        if (pos != -1) {
            int posNextSmallerNumber = findNextSmallerNumber(pos, numbers);
            numbers = swap(pos, posNextSmallerNumber, numbers);
            numbers = sortArrayFromPos(pos, numbers, false);
            System.out.println(Arrays.toString(numbers));
            return toInteger(numbers);
        } else {
            return number;
        }
    }

    private static int findNextSmallerNumber(int pos, int[] numbers) {
        int nextLowerNumber = pos;

        for (int i = pos; i <= numbers.length - 1; i++) {
            if (numbers [nextLowerNumber] > numbers[i]) {
                nextLowerNumber = i;
            }
        }
        return nextLowerNumber;
    }

    public static int toInteger(int [] a) {
        int sum = 0;
        for (int i = 0, j = a.length - 1; i < a.length && j >= 0; i++, j--) {
            sum += a[i] * Math.pow(10, j);
        }
        return sum;
    }
    private static int[] toIntegerArray(int number) {
        int [] numbers = new int[findDigitsInCurrentNumber(number)];

        // Find if it is increasing sequence from right.
        int k = numbers.length - 1;
        while (number != 0) {
            numbers[k] = number % 10;
            number /= 10;
            k--;
        }
        return numbers;
    }

    private static int[] sortArrayFromPos(int pos, int[] numbers, boolean asc) {
        for (int i = pos; i < numbers.length - 1; i++) {
            for (int j = i; j < numbers.length - 1; j++) {
                if (asc && numbers[i] > numbers[j]) {
                  swap(i, j, numbers);
                } else if (!asc && numbers[i] < numbers[j]) {
                    swap(i, j, numbers);
                }
            }
        }

        return numbers;
    }

    private static int[] swap(int pos, int posNextBigNumber, int[] numbers) {
        int temp = numbers[pos];
        numbers[pos] = numbers[posNextBigNumber];
        numbers[posNextBigNumber] = temp;

        return numbers;
    }

    private static int findNextBigNumber(int pos, int[] numbers) {
        int nextHigh = pos;
        for(int i = pos; i < numbers.length - 1; i++) {
            if (numbers[i + 1] > numbers[pos]) {
                if (numbers[nextHigh] < numbers[i + 1]) {
                    nextHigh = i + 1;
                }
            }
        }

        return nextHigh;
    }
}
