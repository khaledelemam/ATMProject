package atm.Model;

import atm.Model.CashManager;
import atm.Model.transactions.WithdrawException;
import com.sun.prism.impl.ps.CachingShapeRep;
import java.util.List;

import java.io.*;

public class CashHandler {
    public CashManager cm;
    public CashHandler() throws IOException{
        cm = new CashManager();
    }

    public void subtractDenominations(double amount, int index) throws WithdrawException, IOException {

        int DecreaseBy = 1;

        if (amount == 0 || cm.getDenominations().length == index) {
            assert true;
        }
        // if the greatestBill is not greater than the amount you're withdrawing
        // and if there is at least one denomination of the greatestBill
        else if (amount - cm.getLargestDenomination(index) >= 0) {
            amount -= cm.getLargestDenomination(index);
            changeDenomination(index, DecreaseBy);
            subtractDenominations(amount, index);
        } else {
            subtractDenominations(amount, index + 1);
        }

    }
    private void changeDenomination(int index, int amount) throws WithdrawException, IOException {
        if (checkDenominationAmount(index)) {
            cm.getBillNumber()[cm.getBillNumber().length - 1 - index] -= amount;
            cm.writeToFile();
        } else {
            throw new WithdrawException();

        }
    }
    private boolean checkDenominationAmount(int index){
        return (cm.getBillNumber()[cm.getBillNumber().length-1-index]) > 0;
    }
    public  List<String> getBillsList(){

        for (int i = 0 ; i < cm.getDenominations().length; i++) {
            int num = cm.getDenominations()[i];
            cm.getBills().add(Integer.toString(num));
        }
        return cm.getBills();

    }
}
