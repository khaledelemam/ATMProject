package atm;

public class InvalidPasswordException extends Exception{
    public InvalidPasswordException(){
        super("Invalid password.");
    }
}
