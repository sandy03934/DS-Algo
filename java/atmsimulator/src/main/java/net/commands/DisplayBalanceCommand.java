package net.commands;

import net.app.InputOutput;
import net.atm.ATMCashManager;

/**
 * Instance of {@link ATMCommand} for displaying the cumulative balance in the ATM.
 * @author Sandip Singh
 */
public class DisplayBalanceCommand implements ATMCommand {

    /**
     * Executes this command. Displays the balance on the console.
     */
    @Override
    public void execute() {
        InputOutput.getInstance().printLine("Available balance: " + ATMCashManager.getInstance().getCurrentBalance());
    }

    /**
     * Returns the description for this command.
     * @return this command description.
     */
    @Override
    public String description() {
        return "Display Balance";
    }
}
