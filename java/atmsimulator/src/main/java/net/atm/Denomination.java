package net.atm;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * All the denomination supported in the {@link ATMCashSlot}
 * @author Sandip Singh.
 */
public enum Denomination {

    /**
     * Denomination of value 10.
     */
    TEN(10),

    /**
     * Denomination of value 20.
     */
    TWENTY(20),

    /**
     * Denomination of value 50.
     */
    FIFTY(50);

    /**
     * Holds the value for the Denomination.
     */
    private Integer val;

    /**
     * Constructor for Denomination Type.
     * @param val The value of the enumeration.
     */
    Denomination(Integer val) {
        this.val = val;
    }

    /**
     * Returns the value for the current enumeration.
     * @return Return the value
     */
    public Integer getVal() {
        return val;
    }

    /**
     * Sets the new value for the current enumeration.
     * @param val the value for the enumeration.
     */
    public void setVal(Integer val) {
        this.val = val;
    }

    /**
     * Checks whether the current value is a valid denomination.
     * @param v value.
     * @return boolean value.
     */
    public static boolean isValid(Integer v) {
       return Stream.of(values()).anyMatch(value -> value.getVal().equals(v));
    }

    /**
     * Returns the enumeration for the given value.
     * @param val  The value for which the enumeration need to be retrieved.
     * @return Instance of {@link Denomination}
     */
    public static Denomination getDenomination(Integer val) {
        return Stream.of(values()).filter(value -> value.getVal().equals(val)).findFirst().orElseThrow(IllegalArgumentException::new);
    }

    /**
     * Returns a list of sorted denominations from the current list of denominations.
     * @return  List of sorted denominations.
     */
    public static List<Integer> getSortedDenominations() {
        Comparator<Integer> ascending = (o1, o2) -> (o1-o2);
        return Stream.of(values()).map(Denomination::getVal).sorted(ascending).collect(toList());
    }

    /**
     * Returns the greatest common denomination from the current enumeration of denominations.
     * @return  The greatest common denomination.
     */
    public static Integer getGreatestCommonDenomination() {
        return Stream.of(values()).map(Denomination::getVal).reduce(0, Denomination::gcd);
    }

    /**
     * Utility method to determine the greatest common divisor for all the denominations supported.
     * @param a  First Integer
     * @param b  Second Integer
     * @return  Greatest common denomination.
     */
    private static Integer gcd(Integer a, Integer b) {
        return (b == 0) ? a : gcd(b, a%b);
    }
}
