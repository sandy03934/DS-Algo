package net.app;

import net.commands.ATMCommand;

import java.util.LinkedHashMap;

/**
 * Options of ATM Commands.
 * @author Sandip Singh
 */
public class ATMOptions extends LinkedHashMap<Integer, ATMCommand> {

    /**
     * Displays the ATMCommand and the number on the console.
     */
    public void show() {
        this.forEach((key, value) -> InputOutput.getInstance().printLine(key + ". " + value.description()));
    }
}
