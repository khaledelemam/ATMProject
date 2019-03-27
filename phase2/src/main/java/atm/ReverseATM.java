package atm;

import java.util.List;

public class ReverseATM {


    public void ReverseTransaction(Transaction trans) throws InsufficientFundsException {
            double amount = trans.getAmount();
            Account from = trans.getRecipient();
            Account to = trans.getSource();
            from.setBalance(-amount);
            to.setBalance(amount);
//        Transaction add = new Transaction(from, to, amount);

            List<Transaction> fromTrans = from.getAllTransactions();
            List<Transaction> toTrans = to.getAllTransactions();
//            if (from.getTransfers().size()> 0){
//                    fromTrans.remove(fromTrans.size()-1);}
//
//            if (to.getTransfers().size()> 0){
//                    toTrans.remove(toTrans.size() - 1);}
            int indexFromTrans = fromTrans.lastIndexOf(trans);
            int indexToTrans = toTrans.lastIndexOf(trans);
            if (fromTrans.size() > 0) {
                fromTrans.remove(indexFromTrans);
            }
            if (toTrans.size() > 0) {
                toTrans.remove(indexToTrans);
            }
        if (toTrans.size() > 0) {
            to.setLastTransaction(toTrans.get(to.getAllTransactions().size() - 1));
        }
        else{
                to.setLastTransaction(null);
        }
        if (fromTrans.size() > 0) {
            from.setLastTransaction(fromTrans.get(from.getAllTransactions().size() - 1));
        }
        else{
                from.setLastTransaction(null);
        }
    }
}





