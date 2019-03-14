package atm;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PayBills implements UserDo{
//    Database Database = new Database();

    private int account;
    private User user;

    public PayBills(int account, User user){
        this.account = account;
        this.user = user;
    }


    public void doTransaction(double amount) throws IOException, InsufficientFundsException {

        Account acc = user.getAccount(account);
        acc.setBalance(-amount);

        PrintWriter billPayer = new PrintWriter(new FileWriter("phase2/src/main/java/atm/outgoing.txt",
                true));
        billPayer.println(user + " payed $" + amount + " on " + "date");
        billPayer.close();

        Transaction bill = new Transaction(acc, amount);
        acc.setLastTransaction(bill);

        Database.store();
    }

}
