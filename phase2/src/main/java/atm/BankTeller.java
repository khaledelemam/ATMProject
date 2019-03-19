package atm;

public class BankTeller extends User{

    /*A BankTeller is a User that has more 'authority' than a regular User
    but less 'authority' than a BankManager
    --A List BankTeller features--
    +Anything a User can do
    +Can create a another User
    +Sets a default password when creating another User(Cannot create another BankTeller)
    +Can read the alerts file (but cannot restock denominations)
    +
    */

    public BankTeller(String username) {
        super(username);
    }

    public void createUser(String username){

    }
}
