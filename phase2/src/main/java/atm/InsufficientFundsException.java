package atm;

public class InsufficientFundsException extends Exception{
    public InsufficientFundsException(){
        super("You have insufficient funds.");
    }

    /** Used for DebtAccounts reaching max debt */
    public InsufficientFundsException(String message){
        super(message);
    }
    }
