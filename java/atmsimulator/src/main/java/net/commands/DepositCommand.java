package net.commands;

import net.app.InputOutput;
import net.atm.ATMCashManager;
import net.exceptions.UnSupportedDenominationException;
import net.exceptions.UnSupportedCurrencyExceptionConsumer;
import net.ledger.ATMLedgerManager;
import net.ledger.Ledger;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

/**
 * Command of type {@link ATMCommand} which deposits the Amount taking input from tty console.
 * @author Sandip Singh
 */
public class DepositCommand implements ATMCommand {

    /**
     * Predicate to validate if the currency deposited in valid or not while depositing into {@link ATMCashManager}
     * @param consumer  Wrapper consumer for {@link UnSupportedCurrencyExceptionConsumer}
     * @return  Instance of {@link Predicate}
     */
    private static Predicate<Integer> validCurrencyDepositFilter(UnSupportedCurrencyExceptionConsumer<Integer> consumer) {
        return i -> {
            try {
                consumer.accept(i);
                InputOutput.getInstance().printLine("Accepted: " + i);
                return true;
            } catch(UnSupportedDenominationException e) {
                InputOutput.getInstance().printLine("Invalid denomination " + i + "$");
                return false;
            }
        };
    }

    /**
     * Execute the deposit action.
     */
    @Override
    public void execute() {
        InputOutput inputOutput = InputOutput.getInstance();
        Scanner scanner = inputOutput.getScanner();

        inputOutput.printLine("Enter ccy to deposit terminated by . e.g. 10 20 50 .");

        List<Integer> currencies = new ArrayList<>();

        while (scanner.hasNextInt()) {
            currencies.add(scanner.nextInt());
        }
        if (scanner.hasNext()) {
            scanner.next();
        }

        Double depositAmt = currencies.stream().filter(validCurrencyDepositFilter(e -> ATMCashManager.getInstance().deposit(e))).mapToDouble(e -> e).sum();
        if (depositAmt > 0) {
            ATMLedgerManager.getInstance().addLedger(new Ledger(Ledger.LedgerType.CREDIT, depositAmt, ATMCashManager.getInstance().getCurrentBalance()));
        }
    }

    /**
     * Returns the description for this command.
     * @return description of this command.
     */
    @Override
    public String description() {
        return "Deposit";
    }
}
