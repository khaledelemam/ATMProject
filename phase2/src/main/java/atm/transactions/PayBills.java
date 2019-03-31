package atm.transactions;

import atm.*;
import atm.accounts.Account;
import atm.users.User;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PayBills implements UserDo {
//    Database Database = new Database();

    private Account source;
    private User user;

    public PayBills(Account account, User user){
        this.source = account;
        this.user = user;
    }

    public void doTransaction(double amount) throws IOException, InsufficientFundsException {
        source.setBalance(-amount);

        Filename outgoingFileName = new Filename();
        PrintWriter billPayer = new PrintWriter(new FileWriter(outgoingFileName.getOutgoingFile(),
                true));

        Transaction bill = new Transaction(source, amount, TransactionType.PayBill);

        source.setLastTransaction(bill);

        source.getAllTransactions().add(bill);

        billPayer.println(user + " payed $" + amount + " on " + bill.getDate());
        billPayer.close();

        Database Database = new Database();
        Database.store();
    }

}
