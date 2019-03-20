package atm;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PayBills implements UserDo{
//    Database Database = new Database();

    private Account source;
    private User user;
    private Time date;

    public PayBills(Account account, User user, Time date){
        this.source = account;
        this.user = user;
        this.date = date;
    }

    public void doTransaction(double amount) throws IOException, InsufficientFundsException {
        source.setBalance(-amount);

        // TODO: this should call the file constant!
        PrintWriter billPayer = new PrintWriter(new FileWriter("phase2/src/main/java/atm/outgoing.txt",
                true));
        // TODO: date object not in this clasS?
        billPayer.println(user + " payed $" + amount + " on " + date);
        billPayer.close();

        Transaction bill = new Transaction(source, amount);
        // TODO: ?
//        source.setLastTransaction(bill);

        Database.store();
    }

}
