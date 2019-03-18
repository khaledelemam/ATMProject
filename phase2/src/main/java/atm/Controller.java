package atm;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static java.time.temporal.ChronoUnit.MINUTES;
import static java.time.temporal.ChronoUnit.SECONDS;

public class Controller implements Initializable {

    // ---- login ----
    public AnchorPane loginScreen;
    public TextField login_passwordField;
    public Button loginButton;
    public Button newAccountButton;
    public Button adminButton;
    public TextField login_usernameField;
    public Label loginMessage;

    // ---- new user ----
    public AnchorPane newUserScreen;
    public Button backButton;
    public TextField new_usernameField;
    public Button requestUserAccountButton;
    public Label newUserMessage;


    // ---- admin ----
    public AnchorPane adminScreen;
    public Label adminMessage;
    public Button acceptNewUserRequestsButton, accountRequestsButton;
    public Button adminLogoutButton;
    public Button reverseTransactionButton;
    public ComboBox adminAccount_cbox;
    public Button addBillsButton;
    public Label adminCashMessage;
    public TextField admin_50field;
    public Label admin_cashTotal;
    public TextField admin_20field;
    public TextField admin_5field;
    public TextField admin_10field;
    public Label adminAlertMessage;

    // ---- user ----
    public AnchorPane userScreen;

    // main
    public Button changePasswordButton;
    public TextField newPasswordField;
    public Label newPasswordMessage;
    public ToggleGroup newAccountsGroup;
    public RadioButton chequingRadioButton, savingsRadioButton,
            creditRadioButton, lineRadioButton, jointRadioButton;
    public TextField shareAccountField;
    public Button requestAccountButton;
    public Label requestAccountMessage;
    public Button logoutButton;

    // accounts
    public ComboBox<String> accounts_cbox;
    public Button accounts_showAccountsButton;
    public Label accounts_infoArea;

    // transfers
    public ComboBox<String>  externalTransfer_cbox, internalTransferTO_cbox,
            internalTransferFROM_cbox,  billPay_cbox;
    public Button internalTransferButton;
    public Button externalTransferButton;
    public Button payBillButton;
    public Label internalTransferMessage;
    public Label billPayMessage;
    public Label externalTransferMessage;

    // withdraw/deposit
    public Button depositButton;
    public TextField depositAmountField;
    public Button withdrawButton;
    public Label totalDeposited;
    public Label withdrawalMessage;
    public Button setTimeButton;
    public Button admin_showUserAccountsButton;
    // end

    // initialize ATM
    atmRunner atm = new atmRunner();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // time stuff
        LocalTime time = ZonedDateTime.now().toLocalTime().truncatedTo(MINUTES);
        System.out.println(time.toString());
        LocalTime midnight = LocalTime.MAX;
        long terminate = SECONDS.between(time, midnight);
        Time date = new Time(1);

        // set radio buttons
        chequingRadioButton.setUserData(1);
        savingsRadioButton.setUserData(2);
        lineRadioButton.setUserData(3);
        creditRadioButton.setUserData(4);
        jointRadioButton.setUserData(5);
    }

    // ----- helpers ^___^  ------

    private void clearLoginFields() {
        login_usernameField.setText("");
        login_passwordField.setText("");
        loginMessage.setText("");
    }

    private void userSessionSetUp() {
        ObservableList<String> accounts = FXCollections.observableArrayList(atm.getAccounts());
        accounts_cbox.setItems(accounts);
        internalTransferTO_cbox.setItems(accounts);
        internalTransferFROM_cbox.setItems(accounts);
        externalTransfer_cbox.setItems(accounts);
        billPay_cbox.setItems(accounts);
    }

    // ----- login events ------
    public void userLogin(ActionEvent actionEvent) {
        if (atm.userLogin(login_usernameField.getText(), login_passwordField.getText())) {
            loginScreen.setVisible(false);
            userScreen.setVisible(true);
            clearLoginFields();
            userSessionSetUp();
        } else {
            clearLoginFields();
            loginMessage.setText("Invalid username or password.");
        }
    }

    public void newUser(ActionEvent actionEvent) {
        clearLoginFields();
        loginScreen.setVisible(false);
        newUserScreen.setVisible(true);
    }

    public void adminLogin(ActionEvent actionEvent) {
        if (atm.adminCheck(login_usernameField.getText(), login_passwordField.getText())) {
            clearLoginFields();
            adminScreen.setVisible(true);
            loginScreen.setVisible(false);
            // TODO: have the cash alert return a string instead of printing
        } else {
            clearLoginFields();
            loginMessage.setText("Admin access denied");
    }
    }

    // ----- new user events -----
    public void goBack(ActionEvent actionEvent) {
        newUserMessage.setText("");
        newUserScreen.setVisible(false);
        loginScreen.setVisible(true);
    }

    public void requestUserAccount (ActionEvent actionEvent) {
        newUserMessage.setText(atm.newUserRequest(new_usernameField.getText()));
    }

    // ----- admin events -----

    public void acceptNewAccountRequests(ActionEvent actionEvent) {
        atm.acceptNewAccountRequests();
        adminMessage.setText("Requests accepted.");
    }

    public void acceptNewUserRequests(ActionEvent actionEvent) {
        atm.acceptNewUserRequests();
        adminMessage.setText("New users created.");
    }

    public void setTime(ActionEvent actionEvent) {


    }

    public void adminLogout(ActionEvent actionEvent) {
        adminAlertMessage.setText("");
        adminMessage.setText("");
        loginScreen.setVisible(true);
        adminScreen.setVisible(false);
    }

    public void addBills(ActionEvent actionEvent) {
    }

    public void reverseLastTransaction(ActionEvent actionEvent) {
    }

    // ----- user events -----

    public void changePassword(ActionEvent actionEvent) {
        atm.changePassword(newPasswordField.getText());
        newPasswordMessage.setText("Password changed");
    }

    public void requestAccount(ActionEvent actionEvent) {
        int index = (int) newAccountsGroup.getSelectedToggle().getUserData();
        atm.requestNewAccount(index, shareAccountField.getText());
        requestAccountMessage.setText("Account requested.");
        shareAccountField.setVisible(false);
        shareAccountField.setText("");
    }

    public void showShareAccount(ActionEvent actionEvent) {
        shareAccountField.setVisible(true);
    }

    public void hideShareAccount(ActionEvent actionEvent) {
        shareAccountField.setVisible(false);
    }

    public void logout(ActionEvent actionEvent) {
        atm.logout();
        userScreen.setVisible(false);
        loginScreen.setVisible(true);
    }

    public void showAccountInfo(ActionEvent actionEvent) {
        int index = 1 + accounts_cbox.getSelectionModel().getSelectedIndex();
        accounts_infoArea.setText(atm.viewAccountInfo(index));
    }

    public void deposit(ActionEvent actionEvent) {
    }

    public void withdraw(ActionEvent actionEvent) {
    }

    public void internalTransfer(ActionEvent actionEvent) {
    }

    public void externalTransfer(ActionEvent actionEvent) {
    }

    public void payBill(ActionEvent actionEvent) {
    }

    public void showUserAccounts(ActionEvent actionEvent) {
    }
}

// move these!

