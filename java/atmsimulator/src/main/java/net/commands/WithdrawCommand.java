package net.commands;

import net.app.InputOutput;
import net.atm.ATMCashManager;
import net.exceptions.InSufficientFundException;
import net.ledger.ATMLedgerManager;
import net.ledger.Ledger;

import java.util.List;
import java.util.Scanner;

/**
 * Instance of {@link ATMCommand} for withdrawing a given amount from ATM taking input from the tty console.
 * @author Sandip Singh.
 */
public class WithdrawCommand implements ATMCommand {

    /**
     * Executes this command. Taking input from the console.
     */
    @Override
    public void execute() {
        InputOutput inputOutput = InputOutput.getInstance();
        Scanner scanner = inputOutput.getScanner();
        InputOutput.getInstance().printLine("Enter an amount to withdraw ");

        Integer amount = scanner.nextInt();
        Double totalCashAmount = ATMCashManager.getInstance().getCurrentBalance();
        if (amount > totalCashAmount) {
            InputOutput.getInstance().printLine("Unable to dispense cash");
        } else {
            List<Integer> dispensedCash = null;
            try {
                dispensedCash = ATMCashManager.getInstance().withdraw(amount);
            } catch (InSufficientFundException e) {
                InputOutput.getInstance().printLine("Insufficient Funds ");
            } catch (IllegalArgumentException ille) {
                InputOutput.getInstance().printLine(ille.getMessage());
            }
            if (dispensedCash != null && !dispensedCash.isEmpty()) {
                dispensedCash.stream().map(e -> "Dispensing " + e + "$").forEach(InputOutput.getInstance()::printLine);
                ATMLedgerManager.getInstance().addLedger(new Ledger(Ledger.LedgerType.DEBIT, (double) amount,  totalCashAmount - (double) amount));
            }
        }
    }

    /**
     * Returns the description for this command.
     * @return  The description for this command.
     */
    @Override
    public String description() {
        return "Withdraw";
    }
}
