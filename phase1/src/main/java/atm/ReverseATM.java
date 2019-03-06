package atm;

public class ReverseATM {


    public void ReverseWithdrawal(User user, Account acc, double amount) throws InsufficientFundsException{
//        System.out.println(amount);
//        System.out.println(acc.getBalance());
        acc.setBalance(amount);
//        System.out.println(acc.getBalance());
        Transaction add = new Transaction(acc, amount);
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





