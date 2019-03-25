package atm;

public class Filename {

    /** Filenames used in CashManager */
    private String cashFile = "phase2/src/main/java/atm/cash.txt";
    private String alertFile = "phase2/src/main/java/atm/alerts.txt";
    private String billFile = "phase2/src/main/java/atm/BillTypes.txt";

    /** Filename used in Main */
    private String fxmlFile = "Interface.fxml";
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

    public String getBillFile(){
        return billFile;
    }

    public String getFxmlFile() {
        return fxmlFile;
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
