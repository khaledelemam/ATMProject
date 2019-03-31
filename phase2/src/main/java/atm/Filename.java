package atm;

import java.io.Serializable;

public class Filename implements Serializable {

    private static final long serialVersionUID = 200L;

    /** Filenames used in CashManager */
    public String getCashFile() { return "phase2/src/main/java/atm/textFiles/cash.txt"; }

    public String getAlertFile() {
        return "phase2/src/main/java/atm/textFiles/alerts.txt";
    }


    /** File path for outgoing file used in PayBills */
    public String getOutgoingFile() { return "phase2/src/main/java/atm/textFiles/outgoing.txt";}

    /** FXML files */
    public String getLoginFile() {
        return "InterfaceLogin.fxml";
    }

    public String getAdminFile(){return "InterfaceAdmin.fxml";}

    public String getBankInternFile(){return "InterfaceBankIntern.fxml";}

    public String getNewUserFile(){return "InterfaceNewUser.fxml";}

    public String getUserFile(){return "InterfaceUser.fxml";}

    /** Filename used in Time */
    public String getTimeFile() {return "Time.ser";}

    /** Serial file used in Database */
    public String getUsersFile() {return "Users.ser";}

    /** Serial file used in BankManager */
    public String getRequestsFile() {return "NewUsersRequests.ser";}


}
