package net.atm;

import java.util.LinkedHashMap;

/**
 * ATMCashSlot for the different {@link Denomination}.
 * @author Sandip Singh
 */
public class ATMCashSlot extends LinkedHashMap<Denomination, Integer> {

    /**
     * Checks whether there is any more cash left for the given denomination in the slot.
     * @param denomination   Denomination as an Integer value.
     * @return Boolean value.
     */
    boolean hasMoney(Integer denomination) {
        Integer value = this.get(Denomination.getDenomination(denomination));
        return value != null && value > 0;
    }

}
