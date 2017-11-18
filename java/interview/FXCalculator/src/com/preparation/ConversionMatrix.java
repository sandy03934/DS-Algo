package com.preparation;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Sandip Singh.
 */
public class ConversionMatrix {

    private static volatile ConversionMatrix matrix = null;

    private ConversionMatrix() {
    }

    public static ConversionMatrix getInstance() {
        if (matrix == null) {
            synchronized (ConversionMatrix.class) {
                if (matrix == null) {
                    matrix = new ConversionMatrix();
                }
            }
        }
        return matrix;
    }

    private Map<String, Map<String, Double>> indexes = new HashMap<>();

    public void addConversion(String conversion, String rate) {
        String fromCurr = conversion.substring(0, 3);
        String toCurr = conversion.substring(3);

        double fromTo = Double.parseDouble(rate);
        double toFrom = 1 / fromTo;

        if (indexes.containsKey(fromCurr)) {
            indexes.get(fromCurr).put(toCurr, fromTo);
        } else {
            Map<String, Double> fromToConversion = new HashMap<>();
            fromToConversion.put(toCurr, fromTo);
            indexes.put(fromCurr, fromToConversion);
        }

        if (indexes.containsKey(toCurr)) {
            indexes.get(toCurr).put(fromCurr, toFrom);
        } else {
            Map<String, Double> toFromConversion = new HashMap<>();
            toFromConversion.put(fromCurr, toFrom);
            indexes.put(toCurr, toFromConversion);
        }
    }

    public double findConversionRate(String fromCurr, String toCurr) {

        if (fromCurr.equals(toCurr)) {
            return 1d;
        }
        if (indexes.containsKey(fromCurr)) {
            if (indexes.get(fromCurr).containsKey(toCurr)) {
                return indexes.get(fromCurr).get(toCurr);
            } else {
                Map<String, Double> convMap = indexes.get(fromCurr);
                for (Map.Entry<String, Double> entry : convMap.entrySet()) {
                    if (indexes.get(entry.getKey()).containsKey(toCurr)) {
                        return indexes.get(entry.getKey()).get(toCurr);
                    }
                }
                throw new IllegalArgumentException("Currency Conversion not available");
            }
        } else {
            throw new IllegalArgumentException("Currency conversion not available");

        }
    }
}
