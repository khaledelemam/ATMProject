package atm.Model.users;

import atm.Model.BankWork;
import atm.Model.CashManager;

import java.io.*;

public class BankIntern extends User implements BankWork {
    private static final long serialVersionUID = 69L;

    public BankIntern(String username) {
        super(username);
    }

    public BankIntern(){}

    public void ReStockATM(int amount, int bill) throws IOException{
        CashManager cashManager = new CashManager();
        cashManager.ReStockATM(amount, bill);
    }

}
