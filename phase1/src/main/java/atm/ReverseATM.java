package atm;

public class ReverseATM {


    public void ReverseWithdrawal(User user, Account acc, Transaction trans) throws InsufficientFundsException{
//        System.out.println(amount);
//        System.out.println(acc.getBalance());
        double amount = trans.getAmount();
        acc.setBalance(amount);
//        System.out.println(acc.getBalance());
        Transaction add = new Transaction(acc, amount);
        acc.setLastTransaction(add);

    }


    public void ReverseTransaction(Account acc, Transaction trans) throws InsufficientFundsException{
        double amount = trans.getAmount();
        Account from = trans.getRecipient();
        Account to = trans.getSource();
        from.setBalance(-amount);
        to.setBalance(amount);
        Transaction add = new Transaction(from, to, amount);
        acc.setLastTransaction(add);

    }
//    public void ReverseinternalTransfer(int from, int to , int amount) throws InsufficientFundsException {
//        Account accFrom = user.getAccount(from);
//        Account accTo = user.getAccount(to);
//        accFrom.setBalance(-amount);
//        accTo.setBalance(amount);
//        Transaction intTransfer = new Transaction(accFrom, accTo, amount);
//        accTo.setLastTransaction(intTransfer);
//        accFrom.setLastTransaction(intTransfer);
//        bankManager.store();
//    }

}





