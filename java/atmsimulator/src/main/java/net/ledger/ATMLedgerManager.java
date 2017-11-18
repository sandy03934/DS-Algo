package net.ledger;

import java.util.ArrayList;
import java.util.List;

/**
 * ATMLedger manager class managing the transactions in the ATM.
 * @author Sandip Singh.
 */
public class ATMLedgerManager {

    /**
     * Instance of the ATMLedgerManager.
     */
    private static ATMLedgerManager instance = null;

    /**
     * A list to hold all the Ledgers.
     */
    private List<Ledger> ledgers = new ArrayList<>();

    /**
     * Returns the instance reference for this {@link ATMLedgerManager}
     * @return Instance of {@link ATMLedgerManager}
     */
    public static ATMLedgerManager getInstance() {
        if (instance == null) {
            synchronized (ATMLedgerManager.class) {
                if (instance == null) {
                    instance = new ATMLedgerManager();
                }
            }
        }
        return instance;
    }

    /**
     * Hidden Constructor
     */
    private ATMLedgerManager() {
    }

    /**
     * Add a new ledger record.
     * @param ledger Instance of {@link Ledger}
     */
    public void addLedger(Ledger ledger) {
        this.ledgers.add(ledger);
    }

    /**
     * Returns a list of Ledger Records hold by this manager.
     * @return List of ledger records.
     */
    public List<Ledger> getLedgers() {
        return this.ledgers;
    }

}
