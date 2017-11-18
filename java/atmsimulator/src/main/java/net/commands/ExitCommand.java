package net.commands;

import net.app.InputOutput;

/**
 * Instance of the {@link ATMCommand}
 * @author Sandip Singh
 */
public class ExitCommand implements ATMCommand {

    /**
     * Displays greeting on the console.
     */
    @Override
    public void execute() {
        InputOutput.getInstance().printLine("Have a good day");
    }

    /**
     * Returns the description of this command.
     * @return  the description for this command.
     */
    @Override
    public String description() {
        return "Exit";
    }
}
