package atm;

import java.io.Serializable;

public class Filename implements Serializable {

    private static final long serialVersionUID = 200L;

    /** Filenames used in CashManager */
    private String cashFile = "phase2/src/main/java/atm/cash.txt";
    private String alertFile = "phase2/src/main/java/atm/alerts.txt";

    /** File path for outgoing file used in PayBills */
    private String outgoingFile = "phase2/src/main/java/atm/outgoing.txt";

    /** FXML files */
    private String loginFile = "InterfaceLogin.fxml";
    private  String adminFile = "InterfaceAdmin.fxml";
    private String bankInternFile = "InterfaceBankIntern.fxml";
    private String newUserFile = "InterfaceNewUser.fxml";
    private String userFile = "InterfaceUser.fxml";

    /** Filename used in Time */
    private String timeFile = "time";

    /** Serial file used in Database */
    private String usersFile = "Users";

    /** Serial file used in BankManager */
    private String requestsFile = "newUsersRequest";

    public String getCashFile() {
        return cashFile;
    }

    public String getAlertFile() {
        return alertFile;
    }

    public String getLoginFile() {
        return loginFile;
    }

    public String getOutgoingFile() {
        return outgoingFile;
    }

    public String getTimeFile() {return timeFile;}

    public String getUsersFile() {return usersFile;}

    public String getRequestsFile() {return requestsFile;}

    public String getAdminFile(){return adminFile;}

    public String getBankInternFile(){return bankInternFile;}

    public String getNewUserFile(){return newUserFile;}

    public String getUserFile(){return userFile;}

}
