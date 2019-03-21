package atm;

import java.io.Serializable;

/** Names for types of accounts (use instead of strings) */
public enum AccountType implements Serializable {
    CHEQUING, SAVINGS, LINEOFCREDIT, CREDIT, JOINT, LOTTERY
}
