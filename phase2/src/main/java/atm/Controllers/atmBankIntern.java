package atm.Controllers;

import atm.Model.CashHandler;
import atm.Model.CashManager;
import atm.Model.users.BankIntern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

public class atmBankIntern {


    private BankIntern bankIntern;

    public atmBankIntern(BankIntern bankIntern){
        this.bankIntern = bankIntern;

    }

    public ObservableList<String> getBills() throws IOException {
        CashHandler ch = new CashHandler();
        return FXCollections.observableArrayList(ch.getBillsList());
    }

    public String addBills(int amount, int bill){
        try {
            bankIntern.ReStockATM(amount, bill);
            return "Bill re-stocked";
        }catch (IOException e){
            return "Error!";
        }

    }
}
