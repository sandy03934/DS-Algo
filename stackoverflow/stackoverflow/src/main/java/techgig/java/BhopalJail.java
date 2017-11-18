package techgig.java;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by sinsandi on 4/14/2017.
 */
public class BhopalJail {

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int output = 0;
        int ip1 = Integer.parseInt(in.nextLine().trim());
        int ip2 = Integer.parseInt(in.nextLine().trim());
        int ip3_size = 0;
        ip3_size = Integer.parseInt(in.nextLine().trim());
        int[] ip3 = new int[ip3_size];
        int ip3_item;
        for(int ip3_i = 0; ip3_i < ip3_size; ip3_i++) {
            ip3_item = Integer.parseInt(in.nextLine().trim());
            ip3[ip3_i] = ip3_item;
        }
        output = GetJumpCount(ip1,ip2,ip3);
        System.out.println(String.valueOf(output));
    }

    public static int GetJumpCount(int input1,int input2,int[] input3)
    {
        int numberOfJumps = 0;

        for (int i = 0; i < input3.length; i++) {
            int height = input3[i];
            while (height > 0) {
                height = height - input1;
                if (height > 0) {
                    height = height + input2;
                }
                numberOfJumps++;
            }
        }

        return numberOfJumps;
    }
}
