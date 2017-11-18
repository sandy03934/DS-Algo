package net.commands;

/**
 * Interface defining the different commands for the ATM.
 * @author Sandip Singh.
 */
public interface ATMCommand {

    /**
     * Executes the command.
     */
    void execute();

    /**
     * Returns the description for the command.
     * @return  Command Description.
     */
    String description();
}
