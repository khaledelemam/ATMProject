package atm;

public class Filename {

    /** Filenames used in CashManager */
    private String cashFile = "phase2/src/main/java/atm/cash.txt";
    private String alertFile = "phase2/src/main/java/atm/alerts.txt";

    /** Filename used in Main */
    private String fxmlFile = "InterfaceLogin.fxml";

    /** File path for outgoing file used in PayBills */
    private String outgoingFile = "phase2/src/main/java/atm/outgoing.txt";
//
//    /** Filename used in Time */
//    private String timeFile = "time";
//
//    /** Serial file used in Database */
//    private String databaseFile = "Users";
//
//    /** Serial file used in BankManager */
//    private String requestsFile = "newUsersRequest";

    public String getCashFile() {
        return cashFile;
    }

    public String getAlertFile() {
        return alertFile;
    }

    public String getFxmlFile() {
        return fxmlFile;
    }

    public String getOutgoingFile() {
        return outgoingFile;
    }

    //    public String getTimeFile() {
//        return timeFile;
//    }

//    public String getDatabaseFile() {
//        return databaseFile;
//    }

//    public String getRequestsFile() {
//        return requestsFile;
//    }
}
