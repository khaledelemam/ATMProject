package atm;

public class UsernameTakenException extends Exception {
    public UsernameTakenException(){
        super("Sorry, this username is already taken. Please choose a new username.");
    }
}
