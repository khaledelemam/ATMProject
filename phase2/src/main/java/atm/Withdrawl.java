package atm;

import java.io.IOException;
import java.security.PrivateKey;

public class Withdrawl  implements UserDo {
//    private Database Database = new Database();
    private BankManager bankmanager = new BankManager();

    private int[] cashAmounts;
    private int account ;
    private int amountTotal;
    private User user;
    private  String username;

    public Withdrawl(int account, String username, int[] cash){
        this.username = username;
        this.account = account;
        this.cashAmounts = cash;
//        amountTotal = (cashAmounts[0] * 5) +
//                (cashAmounts[1] * 10) +
//                (cashAmounts[2] * 20) +
//                (cashAmounts[3] * 50);

    }


    public void doTransaction(double amount) throws InsufficientFundsException {
        user = Database.checkExistingUser(username);
        Account acc = user.getAccount(account);
        acc.setBalance(-amount);
        Transaction withdrawal = new Transaction(acc, amount);
        acc.setLastTransaction(withdrawal);
        Database.store();

        try {
            CashManager cashManager = new CashManager();
            cashManager.changeDenom(5, -cashAmounts[0]);
            cashManager.changeDenom(10, -cashAmounts[1]);
            cashManager.changeDenom(20, -cashAmounts[2]);
            cashManager.changeDenom(50, -cashAmounts[3]);
            cashManager.update();
        } catch (NegativeDenominationException e){
            e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
