import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MinCoins {


    public static int getMinCoinsDP(int[] values, int sum) {
        int min = 0;
        int[] minCoins = new int[sum+1];
        int [] coins = new int[sum + 1];
        int coin = 1;
        minCoins[0] = 0;
        for(int i = 1; i <= sum; i++) {
            min = Integer.MAX_VALUE;
            for (int j = 0; j < values.length; j++) {
                if (i >= values[j]) {
                    min = Math.min(min, minCoins[i - values[j]]);
                    coin = j;
                }
            }
            if (min != Integer.MAX_VALUE) {
                minCoins[i] = min + 1;
                coins[i] = coin;
            } else {
                minCoins[i] = Integer.MAX_VALUE;
            }
//            printArray(minCoins);
//            printArray(coins);

        }
        int a = sum;
        while (a > 0) {
            System.out.print(values[coins[a]] + " ");
            a = a - values[coins[a]];
        }
        return minCoins[sum];
    }

    public static int getMinCoinsG(int[] values, int [] numbers, int sum) {
        int[] minCoins = new int[sum+1];
        int [] coins = new int[sum + 1];

        for (int i = 1; i <= sum; i++) {
            minCoins[i] = Integer.MAX_VALUE;
        }


        int coin = 1;
        minCoins[0] = 0;


        for(int i = 1; i <= sum; i++) {
            int partialSum = 0;
            for (int j = 0; j < values.length; j++) {
                partialSum += values[j] * numbers[j];
                if (i >= values[j] && i <= partialSum && numbers[j] > 0) {
                    int sub_res = minCoins[i - values[j]];
                    if (sub_res != Integer.MAX_VALUE && sub_res + 1 < minCoins[i]) {
                        minCoins[i] = sub_res + 1;
                        coin = j;
                    }
                }
            }
            if (minCoins[i] != Integer.MAX_VALUE) {
                coins[i] = coin;
            }
        }

        printArray(minCoins);
        printArray(coins);

        int a = sum;
        if (minCoins [sum] != Integer.MAX_VALUE) {
            while (a > 0) {
                System.out.print(values[coins[a]] + " ");
                a = a - values[coins[a]];
            }
        } else {
            System.out.println("Unable to make number");
        }
        return minCoins[sum];
    }

    public static int getMinCoins(int[] values, int sum, int [] numbers) {

        Integer maxSum = IntStream.range(0, values.length).map(i -> values[i] * numbers[i]).sum();

        if (sum > maxSum) {
            throw new IllegalArgumentException("Cannot create a huge sum");
        }


        int min = 0;
        int[] minCoins = new int[sum+1];
        int [] coins = new int[sum + 1];
        int coin = 1;
        minCoins[0] = 0;
        for(int i = 1; i <= sum; i++) {
            min = Integer.MAX_VALUE;
            int [] tempNum = new int[numbers.length];
            System.arraycopy(numbers, 0, tempNum, 0, numbers.length);
            for (int j = 0; j < values.length; j++) {
                if (i >= values[j] && numbers[j] > 0) {
                    min = Math.min(min, minCoins[i - values[j]]);
                    if (tempNum[j] > 0) {
                        coin = j;
                        tempNum[j]--;
                    }
//                    numbers[j]--;
                }
            }
            if (min != Integer.MAX_VALUE) {
                minCoins[i] = min + 1;
                coins[i] = coin;
            } else {
                minCoins[i] = Integer.MAX_VALUE;
            }
//            printArray(minCoins);
//            printArray(coins);

        }

        printArray(minCoins);
        printArray(coins);

        int a = sum;
        while (a > 0) {
//            if (numbers[coins[a]] > 0) {
            System.out.print(values[coins[a]] + " ");
            a = a - values[coins[a]];

//                numbers[coins[a]]--;
            }
//        }
        return minCoins[sum];
    }

    static void printArray(int [] arr) {
        IntStream.of(arr).mapToObj(value -> value + "   ").forEach(System.out::print);
        System.out.println();
    }

    public static void main(String [] args) {
        int[] values = {1, 2, 5};
        int[] numbers = {1, 3, 1};
        int sum = 9;
//        System.out.println("Minimum number of coins: "  + getMinCoins(values, sum, numbers));
        System.out.println("Minimum number of coins: "  + getMinCoinsG(values, numbers, sum));

        testStream();

//        makeChangeLimitedCoins(values, numbers, 3);
    }

    private static void testStream() {
        Map<Integer, Object> table = new LinkedHashMap<>();
        table = IntStream.rangeClosed(0, 10).boxed().collect(Collectors.toMap(p -> p * 10, p -> Integer.MAX_VALUE));
        System.out.println(table);

    }


    public static void makeChangeLimitedCoins(int [] D, int [] S, int N) {
        int [] C = new int [N+1];
        C[0] = 0;

        int len = D.length;
        int [][] track = new int[N+1][len];
        for (int i=0; i<len; i++) {
            track[0][i] = S[i];
        }
        int [] denom = new int[N+1];
        for (int j=1; j<=N; j++) {
            C[j] = Integer.MAX_VALUE;
            for (int k=0; k<len ; k++) {
                if (j >= D[k] && (C[j-D[k]] < Integer.MAX_VALUE) && (track[j-D[k]][k] > 0)) {
                //C[j] = C[j] > 1+C[j-D[k]] ? 1+C[j-D[k]] : C[j];
                    if ((C[j] > 1+C[j-D[k]])) {
                        C[j] = 1 + C[j-D[k]];
                        denom[j] = D[k];
                        track[j][k] = track[j-D[k]][k] - 1;
                    } else {
                        track[j][k] = track[j-D[k]][k];
                    }
                } else if (j < D[k] ){
                    track[j][k] = track[0][k];
                }
            }
        }
        System.out.println("Printing Coin Value and Change:");
        for (int i=1; i<=N; i++) {
            if (C[i] == Integer.MAX_VALUE) {
                System.out.println(i + ":" + " Not Possible");
            } else {
                System.out.println(i + ":" + C[i]);
            }
        }
        System.out.println();

        for (int i=0; i<=N ;i++) {
            System.out.print(i + ": ");
            for (int j=0; j<len; j++) {
                System.out.print(track[i][j] + " ");
            }
            System.out.println();
        }

        //System.out.println("Printing change for: " + (N));
        //printCoins(denom, N); 
    }
}
