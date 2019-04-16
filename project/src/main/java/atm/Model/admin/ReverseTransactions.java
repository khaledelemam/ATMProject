package atm.Model.admin;

import atm.Model.Database;
import atm.Model.accounts.Account;
import atm.Model.transactions.InsufficientFundsException;
import atm.Model.transactions.Transaction;
import atm.Model.transactions.TransactionType;

import java.util.List;

public class ReverseTransactions {

    private Database Database = new Database();


    public void ReverseLastTransfer(Account account) throws InsufficientFundsException {
        try {
            switch (account.getLastTransaction().getTransactionType()) {
                case InternalTransfer:
                case ExternalTransfer:
                    Transaction transaction = account.getLastTransaction();
                    ReverseTransferHelper(transaction);
                    break;
                case Deposit:
                case PayBill:
                case Withdraw:
                    if (account.getAllTransactions().size() > 0) {
                        for (int i = account.getAllTransactions().size() - 1; i > 0; i--) {
                            Transaction t = account.getAllTransactions().get(i);
                            if (t.getTransactionType() == TransactionType.InternalTransfer || t.getTransactionType() == TransactionType.ExternalTransfer) {
                                ReverseTransferHelper(t);
                                break;
                            }
                        }
                    }
                    break;
            }
            Database.store();

        } catch (NullPointerException e) {
            throw new NullPointerException("No transactions available.");
        }

    }


    private void ReverseTransferHelper(Transaction trans) throws InsufficientFundsException {
        double amount = trans.getAmount();
        Account from = trans.getRecipient();
        Account to = trans.getSource();
        from.setBalance(-amount);
        to.setBalance(amount);

        List<Transaction> fromTrans = from.getAllTransactions();
        List<Transaction> toTrans = to.getAllTransactions();

        int indexFromTrans = fromTrans.lastIndexOf(trans);
        int indexToTrans = toTrans.lastIndexOf(trans);

        if (fromTrans.size() > 0) {
            fromTrans.remove(indexFromTrans);
        }
        if (toTrans.size() > 0) {
            toTrans.remove(indexToTrans);
        }
        if (toTrans.size() > 0) {
            to.setLastTransaction(toTrans.get(to.getAllTransactions().size() - 1));
        } else {
            to.setLastTransaction(null);
        }
        if (fromTrans.size() > 0) {
            from.setLastTransaction(fromTrans.get(from.getAllTransactions().size() - 1));
        } else {
            from.setLastTransaction(null);
        }
    }

}
