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
    public void subtractDenoms(double amount, int[] cashAmounts, int greatestBill, CashManager cashManager)throws
            NegativeDenominationException, IOException{
        // if the greatestBill is not greater than the amount you're withdrawing
        // and if there is at least one denomination of the greatestBill
        if (amount - cashAmounts[greatestBill] >= 0 && cashManager.checkDenom(greatestBill, 1)) {
            amount -= greatestBill;
            cashManager.changeDenom(greatestBill, 1);
            subtractDenoms(amount, cashAmounts, greatestBill, cashManager);
        }
        else {
            int nextGreatestBill = greatestBill - 1;
            subtractDenoms(amount, cashAmounts, nextGreatestBill, cashManager);
        }
    }

    public void doTransaction(double amount) throws InsufficientFundsException {

        double amountTotal = (cashAmounts[0] * 5) +
                (cashAmounts[1] * 10) +
                (cashAmounts[2] * 20) +
                (cashAmounts[3] * 50);

        if (amountTotal == amount) {

            //TODO: handle incorrect entries in runner eg 11

            Account acc = user.getAccounts().get(account);
            acc.setBalance(-amount);
            Transaction withdrawal = new Transaction(acc, amount);
            acc.setLastTransaction(withdrawal);

            Database Database = new Database();
            Database.store();

            try {
                CashManager cashManager = new CashManager();
                subtractDenoms(amount, cashAmounts, cashAmounts.length, cashManager);
                /*cashManager.changeDenom(5, -cashAmounts[0]);
                cashManager.changeDenom(10, -cashAmounts[1]);
                cashManager.changeDenom(20, -cashAmounts[2]);
                cashManager.changeDenom(50, -cashAmounts[3]);
                */
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
