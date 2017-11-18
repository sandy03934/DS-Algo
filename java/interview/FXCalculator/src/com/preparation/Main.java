package com.preparation;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    initCurrencyChart();

        Scanner scanner = new Scanner(new BufferedInputStream(System.in));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String [] expression = line.split("\\s");

            String fromCurr = expression[0];
            String toCurr = expression[3];

            String amount = expression[1];

            try {
                double rate = ConversionMatrix.getInstance().findConversionRate(fromCurr, toCurr);
                Double convAmount = rate * Double.parseDouble(amount);
                System.out.println(fromCurr + " " + amount + " = " + toCurr + " " + convAmount);
            } catch (IllegalArgumentException e) {
                System.out.println(" Unable to find rate for " + fromCurr + "/" + toCurr);
            }

        }

    }

    private static void initCurrencyChart() {
        try {
            new ConversionMatrixReader().load();
        } catch (IOException e) {
            System.out.println("Failed to load the conversion matrix feed");
            System.exit(0);
        }
    }
}
