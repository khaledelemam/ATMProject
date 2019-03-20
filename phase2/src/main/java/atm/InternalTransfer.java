package atm;

public class InternalTransfer implements UserDo {

//    private Database Database = new Database();

    private Account source;
    private Account destination;

    public InternalTransfer(Account source , Account destination) {
        this.source = source;
        this.destination = destination;
    }

    public void doTransaction(double amount) throws InsufficientFundsException, NullPointerException {
        source.setBalance(-amount);
        destination.setBalance(amount);
        Transaction intTransfer = new Transaction(source, destination, amount);
        destination.setLastTransaction(intTransfer);
        source.setLastTransaction(intTransfer);
        Database.store();
    }
}
