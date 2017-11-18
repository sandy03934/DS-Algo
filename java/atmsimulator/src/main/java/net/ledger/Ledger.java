package net.ledger;

import java.time.LocalDateTime;

/**
 * Class to represent a ledger record. A ledger record holds the audit data for a given ATM Transaction.
 * @author Sandip Singh.
 */
public final class Ledger {

    /**
     * Supported Ledger Types.
     */
    public enum LedgerType {
        CREDIT,
        DEBIT
    }

    /**
     * Holds the Ledger Type.
     */
    private LedgerType type;

    /**
     * Holds the amount for this ledger record.
     */
    private Double amount;

    /**
     * Holds the closing balance when this Ledger Record was made.
     */
    private Double closingBalance;

    /**
     * Holds the createdDate when the transaction was made.
     */
    private LocalDateTime createdDate;

    /**
     * Construtor to create a Ledger Record.
     * @param type   Ledger Type.
     * @param amount   Amount of the ledger.
     * @param closingBalance  Closing Balance.
     */
    public Ledger(LedgerType type, Double amount, Double closingBalance) {
        this.type = type;
        this.amount = amount;
        this.closingBalance = closingBalance;
        this.createdDate = LocalDateTime.now();
    }

    /**
     * Getter for the ledger type.
     * @return  Ledger Type.
     */
    public LedgerType getType() {
        return type;
    }

    /**
     * Returns the amount for the Ledger.
     * @return  Amount for the ledger.
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * Returns the closing balance for this ledger.
     * @return The closing balance for this ledger.
     */
    public Double getClosingBalance() {
        return closingBalance;
    }

    /**
     * Returns the created date for this ledger.
     * @return The created date for this ledger.
     */
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
}
