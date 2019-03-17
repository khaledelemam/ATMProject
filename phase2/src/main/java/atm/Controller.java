package atm;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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

    // ---- user ----
    public AnchorPane userScreen;

    // main
    public Button changePasswordButton;
    public TextField newPasswordField;
    public Label newPasswordMessage;
    public ToggleGroup accountsGroup;
    public RadioButton chequingRadioButton, savingsRadioButton,
            creditRadioButton, lineRadioButton;
    public Button requestAccountButton;
    public Button logoutButton;

    // accounts
    public ComboBox<String> accounts_cbox;
    public Button accounts_showAccountsButton;
    public TextArea accounts_infoArea;

    // transfers
    public ComboBox<String>  internalTransfer_cbox, externalTransfer_cbox;
    public Button internalTransferButton;
    public Button externalTransferButton;
    public ComboBox billPay_cbox;
    public Button payBillButton;

    // withdraw/deposit
    public Button dw_depositButton;
    public TextField dw_amountField;
    public Button dw_withdrawButton;
    public Label dw_message;

    // initialize ATM
    atmRunner atm = new atmRunner();

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    // ----- helpers ^___^  ------
    private void clearLoginFields() {
        login_usernameField.setText("");
        login_passwordField.setText("");
        loginMessage.setText("");
    }

    private void userSessionSetUp() {
        ObservableList<String> accounts = FXCollections.observableArrayList(atm.getAccounts());
        accounts_cbox.setItems(accounts);
        internalTransfer_cbox.setItems(accounts);
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
        atm.acceptNewUserRequests();
        adminMessage.setText("Users Created");
    }

    public void acceptNewUserRequests(ActionEvent actionEvent) {
        atm.acceptExistingUserRequests();
        adminMessage.setText("Requests Accepted");
    }

    public void adminLogout(ActionEvent actionEvent) {
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

    }

    public void logout(ActionEvent actionEvent) {
        atm.logout();
        userScreen.setVisible(false);
        loginScreen.setVisible(true);
    }

    public void showAccountInfo(ActionEvent actionEvent) {
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
}

// move these!

