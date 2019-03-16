package atm;

import java.io.IOException;

public class Withdraw implements UserDo {

    private int[] cashAmounts;
    private int account ;
    private User user;

    public Withdraw(int account, User user, int[] cash){
        this.user = user;
        this.account = account;
        this.cashAmounts = cash;

    }


    public void doTransaction(double amount) throws InsufficientFundsException {

        double amountTotal = (cashAmounts[0] * 5) +
                (cashAmounts[1] * 10) +
                (cashAmounts[2] * 20) +
                (cashAmounts[3] * 50);

        if (amountTotal == amount) {

            //TODO: handle incorrect entries in runner eg 11

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
            } catch (NegativeDenominationException e) {
                e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        else{
            System.out.println("Withdraw invalid. The amount you specified is not equal to the value of the chosen bills.");
        }
    }
}
