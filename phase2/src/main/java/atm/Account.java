package atm;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public abstract class Account implements Serializable {

    double balance;
    private DecimalFormat currencyFormat = new DecimalFormat("0.00");
    private Transaction lastTransaction;
    private Time dateOpened;
    private static final long serialVersionUID = 10L;
//    private List<Transaction> transfers= new ArrayList<>();
    private List<Transaction> allTransactions = new ArrayList<>();
    public Account() {
        this.lastTransaction = null;
        this.balance = 0;
        this.dateOpened = new Time();
    }

    public String getBalance(){
        return currencyFormat.format(balance);
    }

    public abstract void setBalance(double amount) throws InsufficientFundsException;

    public Time getDateOpened() {
        return dateOpened;
    }

    public Transaction getLastTransaction(){
        return lastTransaction;
    }

    public void setLastTransaction(Transaction newTransaction){
        lastTransaction = newTransaction;
    }

//    public void addTransaction(Transaction newTransaction){
//        transfers.add(newTransaction);
//    }


    public abstract  double getNetTotal();
//
//    public List<Transaction> getTransfers(){return this.transfers;}

    public List<Transaction> getAllTransactions(){return this.allTransactions;}

}
