package atm.transactions;

import atm.*;
import atm.accounts.Account;
import atm.users.User;

public class ExternalTransfer implements UserDo {

    private User recipient;
    private Account source;

    public ExternalTransfer(Account source, User recipient) {
        this.recipient = recipient;
        this.source = source;
    }

    public void doTransaction(double amount) throws InsufficientFundsException {
        if (recipient.getPrimaryAccount() != null) {
            Account destination = recipient.getPrimaryAccount();
            source.setBalance(-amount);
            destination.setBalance(amount);

            Transaction extTransfer = new Transaction(source, destination, amount, recipient.getUsername());
            source.getAllTransactions().add(extTransfer);
            source.setLastTransaction(extTransfer);;
            destination.setLastTransaction(extTransfer);

            Database Database = new Database();
            Database.store();
            }
        }
    }

