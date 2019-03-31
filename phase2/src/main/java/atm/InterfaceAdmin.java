package atm;

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


    atmAdmin atm = new atmAdmin(new BankManager());

    public InterfaceAdmin() throws IOException {
        CashManager cm = new CashManager();
        alerts = cm.showAlerts();
        bills = atm.getBills();
        users = atm.getUsers();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        adminUser_cbox.setItems(users);
        adminAlertMessage.setText(alerts);
        addBills_cbox.setItems(bills);

    }

    public void acceptNewAccountRequests(ActionEvent actionEvent) {
        atm.acceptNewAccountRequests();
        adminMessage.setText("Requests accepted.");
    }

    public void acceptNewUserRequests(ActionEvent actionEvent) {
        atm.acceptNewUserRequests();
        adminMessage.setText("New users created.");
    }

    public void advanceTime(ActionEvent actionEvent) {
        try {
            int days = checkInput(daysField.getText());
            atm.advanceDate(days);
            daysField.setText("");
            adminMessage.setText("New date set.");
        } catch (InvalidInputException e) {
            adminMessage.setText(e.getMessage());
        }
    }

    public void addBills(ActionEvent actionEvent) throws IOException {
        CashManager cm = new CashManager();
        try {
            int amount = checkInput(addBillsAmountField.getText());
            int bill = Integer.parseInt(addBills_cbox.getSelectionModel().getSelectedItem());
            adminCashMessage.setText(atm.addBills(amount, bill));
            adminAlertMessage.setText(cm.showAlerts());
        } catch (InvalidInputException e) {
            adminCashMessage.setText(e.getMessage());
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
        Parent mainScreen = FXMLLoader.load(getClass().getResource("InterfaceLogin.fxml"));
        Scene scene = new Scene(mainScreen);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    /** Helper method to check if text input is in correct integer format. */
    private int checkInput(String fieldInput) throws InvalidInputException {
        if (fieldInput.matches("^\\d+")) {
            return Integer.parseInt(fieldInput);
        } else {
            throw new InvalidInputException("Enter a number.");
        }
    }





}
