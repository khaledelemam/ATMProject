package atm;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

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

    ArrayList<String> newUsersRequests = new ArrayList<>();

    public BankTeller(String username) {
        super(username);
    }

    @Override
    public boolean isEmployee() {
        return true;
    }
}
