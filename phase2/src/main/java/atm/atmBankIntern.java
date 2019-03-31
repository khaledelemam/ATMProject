package atm;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

public class atmBankIntern {


    private BankIntern bankIntern;

    public atmBankIntern(BankIntern bankIntern){
        this.bankIntern = bankIntern;

    }

    ObservableList<String> getBills() throws IOException {
        CashManager cm = new CashManager();
        return FXCollections.observableArrayList(cm.getBillsList());
    }

    String addBills(int amount, int bill){
        try {
            bankIntern.ReStockATM(amount, bill);
            return "Bill re-stocked";
        }catch (IOException e){
            return "Error!";
        }

    }
}
