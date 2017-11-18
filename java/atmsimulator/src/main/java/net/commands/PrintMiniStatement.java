package net.commands;

import net.app.InputOutput;
import net.ledger.ATMLedgerManager;
import net.ledger.Ledger;

import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Instance of {@link ATMCommand} to display the mini statement of the transactions of the ATM on the console.
 * @author Sandip Singh
 */
public class PrintMiniStatement implements ATMCommand {

    /**
     * Executes this command. Displays the transactions on the output console.
     */
    @Override
    public void execute() {
        List<Ledger> ledgers = ATMLedgerManager.getInstance().getLedgers();

        InputOutput.getInstance().printLine("--------------------------------------------------------------------------");
        InputOutput.getInstance().printLine("Date Time   Transaction     Amount      Closing Balance");
        InputOutput.getInstance().printLine("--------------------------------------------------------------------------");
        ledgers.forEach(ledger -> {InputOutput.getInstance().printLine(ledger.getCreatedDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + "    " + ledger.getType().toString() + "    " + ledger.getAmount() + "   " + ledger.getClosingBalance());});
        InputOutput.getInstance().printLine("---------------------------------------------------------------------------");
    }

    /**
     * Returns the description for this command.
     * @return The description for this command.
     */
    @Override
    public String description() {
        return "Mini Statement";
    }
}
