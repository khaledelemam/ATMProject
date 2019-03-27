package atm;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BankTeller extends User implements BankWorker{

    private String password;

    public BankTeller(String username) {
        super(username);
    }

    @Override
    public boolean isEmployee() {
        return true;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public void ReverseLastTransaction(Account account) throws InsufficientFundsException {
        ReverseATM rATM = new ReverseATM();
        try {
            switch(account.getLastTransaction().getTransactionType()) {
                case InternalTransfer:
                case ExternalTransfer:
                    Transaction transaction = account.getLastTransaction();
                    rATM.ReverseTransaction(transaction);
                    break;
                case Deposit:
                case PayBill:
                case Withdraw:
                    if (account.getAllTransactions().size()> 0) {
                        for (int i = account.getAllTransactions().size()-1; i >0 ; i--) {
                            Transaction t = account.getAllTransactions().get(i);
                            if (t.getTransactionType() == TransactionType.InternalTransfer || t.getTransactionType() == TransactionType.ExternalTransfer) {
                                Transaction transaction2 = account.getAllTransactions().get(account.getAllTransactions().size() - 1);
                                rATM.ReverseTransaction(transaction2);
                                break;
                            }
                        }
                    }
                    break;
            }
            Database Database = new Database();
            Database.store();

        } catch (NullPointerException e) {
            NullPointerException n = new NullPointerException("No transactions available.");
            throw n;
        }
    }

    void setPassword(String password){
        this.password = password;
    }

}
