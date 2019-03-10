package atm;

public class InvalidUserException extends Exception{
    public InvalidUserException(){
        super("Invalid username.");
    }
}
