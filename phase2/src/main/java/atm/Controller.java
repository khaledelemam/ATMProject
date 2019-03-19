package atm;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import javax.xml.crypto.Data;
import java.net.URL;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
    public Button addBillsButton;
    public Label adminCashMessage;
    public TextField admin_50field;
    public Label admin_cashTotal;
    public TextField admin_20field;
    public TextField admin_5field;
    public TextField admin_10field;
    public Label adminAlertMessage;
    public Button setTimeButton;
    public TextField daysField;

    public ComboBox<User> adminUser_cbox;
    public ComboBox<String> adminAccount_cbox;
    public Button showUserAccountsButton;
    public Label transactionMessage;


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
    public ComboBox<User> recipientUser;
    public Button internalTransferButton, externalTransferButton, payBillButton;
    public Label internalTransferMessage, billPayMessage, externalTransferMessage;
    public TextField externalTransferAmount, internalTransferAmount, billPayAmount;

    // withdraw/deposit
    public Button depositButton, withdrawButton;
    public TextField depositAmountField;
    public Label depositMessage, withdrawalMessage;
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

        adminUser_cbox.setItems(atm.getUsers());
        recipientUser.setItems(atm.getUsers());
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
            adminAlertMessage.setText("<Alert message from cashmanager goes here>");
        } else {
            clearLoginFields();
            loginMessage.setText("Admin access denied");
    }
    }

    // ----- new user events -----
    public void goBack(ActionEvent actionEvent) {
        newUserMessage.setText("");
        new_usernameField.clear();
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

    public void showUserAccounts(ActionEvent actionEvent) {
        // TODO: this doesnt work??? why
        atm.setUser(adminUser_cbox.getSelectionModel().getSelectedItem());
        adminAccount_cbox.setItems(FXCollections.observableArrayList(atm.getAccounts()));
    }

    public void reverseLastTransaction(ActionEvent actionEvent) {
        transactionMessage.setText(atm.reverseTransaction(accounts_cbox.getSelectionModel().getSelectedIndex()));
        atm.setUser((User) null);


    }

    // ----- user events -----

    public void changePassword(ActionEvent actionEvent) {
        atm.changePassword(newPasswordField.getText());
        newPasswordMessage.setText("Password changed");
    }

    public void requestAccount(ActionEvent actionEvent) {
        int index = (int) newAccountsGroup.getSelectedToggle().getUserData();
        requestAccountMessage.setText(atm.requestNewAccount(index, shareAccountField.getText()));
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
        requestAccountMessage.setText("");
        newPasswordMessage.setText("");
        newPasswordField.clear();
        internalTransferMessage.setText("");
        billPayMessage.setText("");
        externalTransferMessage.setText("");
        withdrawalMessage.setText("");
        // depositMessage.setText("");
        userScreen.setVisible(false);
        loginScreen.setVisible(true);
    }

    public void showAccountInfo(ActionEvent actionEvent) {
        accounts_infoArea.setText(atm.viewAccountInfo(accounts_cbox.getSelectionModel().getSelectedIndex()));
    }

    public void deposit(ActionEvent actionEvent) {
        double amount = Double.parseDouble(depositAmountField.getText());

    }

    public void withdraw(ActionEvent actionEvent) {

    }

    public void internalTransfer(ActionEvent actionEvent) {
        int indexTO = internalTransferFROM_cbox.getSelectionModel().getSelectedIndex();
        int indexFROM = externalTransfer_cbox.getSelectionModel().getSelectedIndex();
        // TODO: use regex to control user input amount format
        double amount = Double.parseDouble(internalTransferAmount.getText());
        atm.internalTransfer(indexFROM, indexTO, amount);

    }

    public void externalTransfer(ActionEvent actionEvent) {
        User recipient = recipientUser.getSelectionModel().getSelectedItem();
        Double amount = Double.parseDouble(externalTransferAmount.getText());
        int index = externalTransfer_cbox.getSelectionModel().getSelectedIndex();
        externalTransferMessage.setText(atm.externalTransfer(recipient, amount, index));

    }

    public void payBill(ActionEvent actionEvent) {
        int index = billPay_cbox.getSelectionModel().getSelectedIndex();
        Double amount = Double.parseDouble(billPayAmount.getText());
        billPayMessage.setText(atm.payBill(index, amount));
    }
}

// move these!

