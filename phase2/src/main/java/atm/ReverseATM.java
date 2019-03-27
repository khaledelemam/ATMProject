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

            List<Transaction> fromTrans = from.getTransfers();
            List<Transaction> toTrans = to.getTransfers();
            if (from.getTransfers().size()> 1){
                    fromTrans.remove(fromTrans.size()-1);}
            if (to.getTransfers().size()> 1){
                    toTrans.remove(toTrans.size() - 1);}
            changeLastTransaction(to);
            changeLastTransaction(from);
    }
    private void changeLastTransaction(Account acc){
            switch (acc.getLastTransaction().getTransactionType()){
                    case Withdraw:
                    case Deposit:
                    case PayBill:
                            break;
                    case InternalTransfer:
                    case ExternalTransfer:
                            List <Transaction> transfers = acc.getTransfers();
                            if (acc.getTransfers().size()> 1){
                                    acc.setLastTransaction(transfers.get(transfers.size() -1));}
            }
    }
}





