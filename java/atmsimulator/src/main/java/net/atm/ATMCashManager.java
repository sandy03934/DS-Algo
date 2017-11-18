package net.atm;

import net.exceptions.InSufficientFundException;
import net.exceptions.UnSupportedDenominationException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Cash Manager to manage deposit or withdrawal of currencies in the {@link ATMCashSlot}.
 * @author Sandip Singh
 */
public class ATMCashManager {

    /**
     * Holds the singleton instance of this {@link ATMCashManager}
     */
    private static volatile ATMCashManager instance = null;

    /**
     * Hidden Constructor.
     */
    private ATMCashManager() {}

    /**
     * Instance of {@link ATMCashSlot} which holds the denomination oto number of currencies value.
     */
    private ATMCashSlot cashSlot = new ATMCashSlot();

    /**
     * Accessor for the singleton instance of {@link ATMCashManager}
     * @return Instance of {@link ATMCashManager}
     */
    public static ATMCashManager getInstance() {
        if (instance == null) {
            synchronized (ATMCashManager.class) {
                if (instance == null) {
                    instance = new ATMCashManager();
                }
            }
        }
        return instance;
    }

    /**
     * DepositCommand the cash in the cash slot.
     * @param cash  Cash Value.
     * @throws UnSupportedDenominationException  In case the input cash is not a valid denomination.
     */
    public void deposit(Integer cash) throws UnSupportedDenominationException {
        if (Denomination.isValid(cash)) {
            Integer value =  cashSlot.get(Denomination.getDenomination(cash));
            if (value == null) {
                value = 1;
            } else {
                value = value + 1 ;
            }
            cashSlot.put(Denomination.getDenomination(cash), value);
        } else {
            throw new UnSupportedDenominationException("Invalid denomination");
        }
    }

    /**
     * Returns the cumulative balance of all the cash slots in the {@link ATMCashSlot}
     * @return  Value of all the cash slot.
     */
    public Double getCurrentBalance() {
        return this.cashSlot.entrySet().stream().mapToDouble(entry -> entry.getKey().getVal() * entry.getValue()).sum();
    }

    /**
     * Withdraws the given amount from {@link ATMCashSlot}
     * @param amount  Total amount to be withdrawn.
     * @return  A List of denomination value which are dispensed from the {@link ATMCashSlot}
     * @throws InSufficientFundException  In case {@link ATMCashSlot} doesn't have enough cash to dispense the matching amount.
     */
    public List<Integer> withdraw(final Integer amount) throws InSufficientFundException {

        List<Integer> denominations = Denomination.getSortedDenominations();
        Integer highestCommonDenomination = Denomination.getGreatestCommonDenomination();

        if (amount <  highestCommonDenomination || amount % highestCommonDenomination != 0) {
            throw new IllegalArgumentException("Amount should be multiple of " + highestCommonDenomination);
        }

        Integer noOfCurrencies = amount / highestCommonDenomination;

        Map<Integer, Integer> tableOfSumToMinValues = IntStream.rangeClosed(0, noOfCurrencies).boxed().collect(Collectors.toMap(p -> p * highestCommonDenomination, p -> Integer.MAX_VALUE));
        tableOfSumToMinValues.put(0, 0);
        LinkedHashMap<Integer, Integer> denominationTrackingMap = new LinkedHashMap<>();

        List<Integer> dispensed = new ArrayList<>();
        int coin = 0;
        for (int i = highestCommonDenomination; i <= amount; i = i + highestCommonDenomination) {
            int partialSum = 0;
            for (Integer denomination : denominations) {
                if (cashSlot.hasMoney(denomination)) {
                    partialSum += denomination * cashSlot.get(Denomination.getDenomination(denomination));
                    if (i >= denomination && i <= partialSum) {
                        int sub_res = tableOfSumToMinValues.get(i - denomination);
                        if (sub_res != Integer.MAX_VALUE && sub_res + 1 < tableOfSumToMinValues.get(i)) {
                            tableOfSumToMinValues.put(i, sub_res + 1);
                            coin = denomination;
                        }
                    }
                }
            }

            if (tableOfSumToMinValues.get(i) != Integer.MAX_VALUE) {
                denominationTrackingMap.put(i, coin);
            }
        }

        int a = amount;

        if (tableOfSumToMinValues.get(a) != Integer.MAX_VALUE) {
            while (a > 0) {
                dispensed.add(denominationTrackingMap.get(a));
                a = a - denominationTrackingMap.get(a);
            }
        }

        if (dispensed.isEmpty()) {
            throw new InSufficientFundException();
        } else {
            dispensed.forEach(e -> {
                    Integer count = cashSlot.get(Denomination.getDenomination(e));
                    cashSlot.put(Denomination.getDenomination(e), count - 1);
            });
        }


        return dispensed;
    }
}
