package atm;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BankIntern extends User implements BankWorker{
    private static final long serialVersionUID = 69L;

    public BankIntern(String username) {
        super(username);
    }
    public BankIntern(){

    }

    @Override
    public boolean isEmployee() {
        return true;
    }

    public void ReStockATM(int amount, int bill) throws IOException{
        CashManager cashManager = new CashManager();
        for (int i = 0; i < cashManager.getDenominations().length; i++){
            if (cashManager.getDenominations()[i] == bill){
                cashManager.getBillNumber()[i]  += amount;
            }
        }
        cashManager.writeToFile();
        cashManager.update();

    }

}
