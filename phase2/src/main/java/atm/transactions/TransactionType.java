package atm.transactions;
import java.io.Serializable;

public enum TransactionType implements Serializable{
    ExternalTransfer, InternalTransfer, Withdraw, Deposit, PayBill
}
