package atm.Model.admin;

import atm.Model.*;
import atm.Model.accounts.AccountType;
import atm.Model.accounts.Calculations;
import atm.Model.users.*;
import atm.Model.Filename;

import java.io.Serializable;
import java.util.ArrayList;
import java.io.*;
import java.util.List;

public class BankManager extends People implements Serializable, BankWork {

    private List<List<Object>> newUsersRequests = new ArrayList<>();
    private atm.Model.Database Database = new Database();
    private Filename file = new Filename();


    public BankManager() {
        this.username = "admin";
        this.password = "123";
    }


    private void setUserPassword(User user) {
        String password = "1";
        user.setPassword(password);
        Database.addUser(user);

    }

    public void createUser() {
        retrieveRequests();

        for (List<Object> users: newUsersRequests) {
            if (CreditScore.getRandomDoubleBetweenRange() > 0) {
                String username = (String) users.get(0);
                UserType userType = (UserType) users.get(1);
                switch (userType){
                    case NormalUser:
                        User user = new User(username);
                        user.createAccount(AccountType.CHEQUING);
                        setUserPassword(user);
                        break;
                    case BankIntern:
                        User bankIntern = new BankIntern(username);
                        bankIntern.createAccount(AccountType.CHEQUING);
                        setUserPassword(bankIntern);
                        break;
                }

            }
        }
        newUsersRequests.clear();
        storeRequests();
    }

    public void createAccounts() {
        for (User user : Database.getUsers()) {
            if (CreditScore.getRandomDoubleBetweenRange() > 0) {
                // for joint accounts

                if (user.getAccountRequest() != null && user.getPartner() != null) {
                    //primary user
                    AccountType request = user.getAccountRequest();
                    user.createAccount(request);
                    String partner = user.getPartner();

                    //partner
                    User OtherUser = Database.checkExistingUser(partner);
                    OtherUser.setPartner(user.getUsername());
                    OtherUser.addAccount(user.getJointAccount());

                } //for single accounts
                else if (user.getAccountRequest() != null) {
                    System.out.println(user.getAccountRequest());
                    user.createAccount(user.getAccountRequest());
                }
            }
            Database.store();
        }
    }


    public void newUserRequest(String username, UserType type) throws UsernameTakenException {
        retrieveRequests();

        // checks if the username is in the requests list or a current user has the same username.
        if ((Database.checkExistingUser(username) != null) || (checkUsername(username))) {
            throw new UsernameTakenException();
        } else {
            List<Object> userTypes = new ArrayList<>();
            userTypes.add(username);
            userTypes.add(type);

            newUsersRequests.add(userTypes);
            storeRequests();
            Database.store();
        }
    }


    private boolean checkUsername(String username){
        for(List users: newUsersRequests){
            String user = (String)users.get(0);
            System.out.println(user);
            if (user.equals(username)) {
                    return true;
            }
        }
        return false;

    }
    public void ReStockATM(int amount, int bill) throws IOException{
        CashManager cashManager = new CashManager();
        cashManager.ReStockATM(amount, bill);
    }


    public void setDate(int days, Time oldDate) {
        long plusOneDay = (1000 * 60 * 60 * 24);
        long NumberOfDays = plusOneDay * days;

        Calculations calc = new Calculations();
        calc.numInterestApplications(days, oldDate);

        new Time(NumberOfDays);
    }


    private void storeRequests(){
        Serialize ser = new Serialize(file.getRequestsFile(), newUsersRequests);
        ser.store();
    }

    @SuppressWarnings("unchecked")

    private void retrieveRequests() {
        Serialize ser = new Serialize(file.getRequestsFile(), newUsersRequests);
        Object retrieve = ser.retrieve();
        if (retrieve != null) newUsersRequests = (List<List<Object>>) retrieve;
    }

}
