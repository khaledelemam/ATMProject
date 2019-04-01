package atm.View;

import atm.Model.CashManager;
import atm.Model.Filename;
import atm.Model.Time;
import atm.Model.accounts.Account;
import atm.Model.admin.BankManager;
import atm.Controllers.AdminController;
import atm.Model.users.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** View class for admin functionality in UI.*/
public class InterfaceAdmin implements Initializable{


    public AnchorPane adminScreen;
    public Label date;
    public Label adminMessage;
    public Button acceptNewUserRequestsButton, accountRequestsButton;
    public Button adminLogoutButton;
    public Button reverseTransactionButton;
    public Button addBillsButton;
    public Label adminCashMessage;

    public TextField addBillsAmountField;

    public Label adminAlertMessage;
    public Button setTimeButton;
    public TextField daysField;

    public ComboBox<User> adminUser_cbox;
    public ComboBox<Account> adminAccount_cbox;
    public ComboBox<String> addBills_cbox;
    public Button showUserAccountsButton;
    public Label transactionMessage;

    private String alerts;
    private ObservableList<String> bills;
    private ObservableList<User> users;


    AdminController atm = new AdminController(new BankManager());

    public InterfaceAdmin() throws IOException {
        CashManager cm = new CashManager();
        alerts = cm.showAlerts();
        bills = atm.getBills();
        users = atm.getUsers();

    }

    @Override
    /**Setup the admin screen*/
    public void initialize(URL location, ResourceBundle resources) {
        adminUser_cbox.setItems(users);
        adminAlertMessage.setText(alerts);
        addBills_cbox.setItems(bills);
        Time time = new Time();
        date.setText(time.toString());

    }

    public void acceptNewAccountRequests(ActionEvent actionEvent) {
        atm.acceptNewAccountRequests();
        adminMessage.setText("Requests accepted.");
    }

    public void acceptNewUserRequests(ActionEvent actionEvent) {
        atm.acceptNewUserRequests();
        adminMessage.setText("New users created.");
    }
    /**Advance the date by the number of days that the admin inputs in the daysField.*/
    public void advanceTime(ActionEvent actionEvent) {
        if (checkInput(daysField.getText())){
            int days = Integer.parseInt(daysField.getText());
            atm.advanceDate(days);
            Time time = new Time();
            date.setText(time.toString());
            adminMessage.setText("New date set.");
        }
        else {
            adminMessage.setText("Please enter a number.");
        }
        daysField.setText("");
    }
    /**Restocks admin*/
    public void addBills(ActionEvent actionEvent) throws IOException {
        CashManager cm = new CashManager();
        if(checkInput(addBillsAmountField.getText())){

            int amount = Integer.parseInt(addBillsAmountField.getText());
            int bill = Integer.parseInt(addBills_cbox.getSelectionModel().getSelectedItem());
            adminCashMessage.setText(atm.addBills(amount, bill));
            adminAlertMessage.setText(cm.showAlerts());
        }
        else {
            adminCashMessage.setText("Please enter a number.");
        }
        addBillsAmountField.setText("");
    }

    public void showUserAccounts(ActionEvent actionEvent) {
        atm.setUser(adminUser_cbox.getSelectionModel().getSelectedItem());
        adminAccount_cbox.setItems(FXCollections.observableArrayList(atm.getAccounts()));
    }

    public void reverseLastTransaction(ActionEvent actionEvent) {
        transactionMessage.setText(atm.reverseTransaction(adminAccount_cbox.getSelectionModel().getSelectedItem()));
        atm.setUser(null);
    }

    public void adminLogout(ActionEvent actionEvent) throws IOException {

        Filename f = new Filename();
        Parent mainScreen = FXMLLoader.load(getClass().getResource(f.getLoginFile()));
        Scene scene = new Scene(mainScreen);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    /** Helper method to check if text input is in correct integer format. */
    private boolean checkInput(String fieldInput) {
        return fieldInput.matches("^\\d+");
    }





}
