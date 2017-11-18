package net.app;

import net.commands.ATMCommand;
import net.commands.DepositCommand;
import net.commands.DisplayBalanceCommand;
import net.commands.ExitCommand;
import net.commands.PrintMiniStatement;
import net.commands.WithdrawCommand;

/**
 * Main app for the ATM Simulator. Displays options on the system output and manages ATM Operation.
 * @author Sandip Singh.
 */
public class ATMSimulator {

    /**
     * Main method invovation.
     * @param args  Command line arguments.
     */
    public static void main(String [] args) {
        ATMOptions options = new ATMOptions();
        options.put(1, new DepositCommand());
        options.put(2, new WithdrawCommand());
        options.put(3, new DisplayBalanceCommand());
        options.put(4, new PrintMiniStatement());
        options.put(5, new ExitCommand());

        Integer selectedOption;
        InputOutput inputOutput = InputOutput.getInstance();
        do {
            options.show();
            inputOutput.print("Select an option ");
            selectedOption = inputOutput.getScanner().nextInt();

            ATMCommand command = options.get(selectedOption);
            if (command == null) {
                inputOutput.printLine("Please select valid option");
            } else {
                command.execute();
            }
        } while(!(options.get(selectedOption) instanceof ExitCommand));
    }
}
