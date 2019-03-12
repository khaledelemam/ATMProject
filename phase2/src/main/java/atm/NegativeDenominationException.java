package atm;

public class NegativeDenominationException extends Exception {
    public NegativeDenominationException(){
        super("Will result in a negative denomination");
    }
}
