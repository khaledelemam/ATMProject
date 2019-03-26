package atm;

public class WithdrawException extends Exception{
        public WithdrawException(){ super("Unable to dispense the requested amount"); }
    }

