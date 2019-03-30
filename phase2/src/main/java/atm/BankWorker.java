package atm;

import java.io.IOException;

interface BankWorker {

    void ReStockATM(int amount, int bill) throws IOException;

}
