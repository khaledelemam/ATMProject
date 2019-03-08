package atm;

public class NotEnoughBillsException extends Exception {
    public NotEnoughBillsException(){
        super("Error, not enough bills");
    }
}
