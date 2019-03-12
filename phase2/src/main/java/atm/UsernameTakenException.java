package atm;

public class UsernameTakenException extends Exception {
    public UsernameTakenException(){
        super("Sorry, a user with this username already exists.");
    }
}
