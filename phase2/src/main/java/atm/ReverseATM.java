package atm;

public class ReverseATM {


    public void ReverseTransaction(Account acc, Transaction trans) throws InsufficientFundsException{
        double amount = trans.getAmount();
        Account from = trans.getRecipient();
        Account to = trans.getSource();
        from.setBalance(-amount);
        to.setBalance(amount);
//        Transaction add = new Transaction(from, to, amount);
        acc.setLastTransaction(null);

    }
}





