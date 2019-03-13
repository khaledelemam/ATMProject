package atm;

public class UserView {


    void viewAccounts(String username) {

        User user = Database.checkExistingUser(username);
        user.viewAccounts();
    }



    String viewAccountsInfo(String username) {

        User user = Database.checkExistingUser(username);

        String accInfo = "Net total: " + user.netUserBalance() + "\n";
        for (String account: user.accountInfo()) {
            accInfo += account + "\n";
        }
        return accInfo;
    }

}
