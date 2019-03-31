package atm.users;

public class UsernameTakenException extends Exception {
    public UsernameTakenException(){
        super("Sorry, this username is taken.");
    }
}
