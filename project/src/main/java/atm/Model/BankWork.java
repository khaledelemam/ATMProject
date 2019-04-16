package atm.Model;

import java.io.IOException;

public interface BankWork {

    void ReStockATM(int amount, int bill) throws IOException;

}
