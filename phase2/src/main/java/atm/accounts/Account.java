package atm.accounts;

import atm.transactions.InsufficientFundsException;
import atm.Time;
import atm.transactions.Transaction;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public abstract class Account implements Serializable {
    
    private static final long serialVersionUID = 10L;
    public double balance;
    private DecimalFormat currencyFormat = new DecimalFormat("0.00");
    private Transaction lastTransaction;
    private Time dateOpened;
    private List<Transaction> allTransactions = new ArrayList<>();
    public int accountNumber;
    public AccountType type;


    public Account() {
        this.lastTransaction = null;
        this.balance = 0;
        this.dateOpened = new Time();

    }

    /** Return account balance value to display. */
    public String getBalance(){ return currencyFormat.format(balance); }

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

    public void setNumber(int number) { this.accountNumber = number; }


    /** Return positive or negative (debt accounts) balance to calculate net total. */
    public double getNetTotal(){
        return balance;
    }

    public AccountType getType(){ return type;
    }

    public List<Transaction> getAllTransactions(){return this.allTransactions;}

}
