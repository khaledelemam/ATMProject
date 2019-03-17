package atm;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.awt.peer.LabelPeer;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public AnchorPane loginScreen;
    public TextField login_passwordField;
    public Button requestButton;
    public AnchorPane newUserScreen;
    public Button backButton;
    public TextField new_usernameField;
    public Button loginButton;
    public Button newAccountButton;
    public Button adminButton;
    public TextField login_usernameField;
    public Label loginMessage;
    public TextField passwordField;
    public AnchorPane adminScreen;
    public AnchorPane userScreen;
    public ComboBox accounts_comboBox;
    public Button accounts_showAccountsButton;
    public TextArea accounts_infoArea;
    public Button dw_depositButton;
    public TextField dw_amountField;
    public Button dw_withdrawButton;
    public Label dw_message;
    public ComboBox internalTransfer_cbox;
    public Button internalTransferButton;
    public ComboBox externalTransfer_cbox;
    public Button externalTransferButton;
    public ComboBox billPay_cbox;
    public Button payBillButton;
    public RadioButton chequingRadioButton;
    public RadioButton savingsRadioButton;
    public RadioButton creditRadioButton;
    public RadioButton lineRadioButton;
    public Button requestAccountButton;
    public ToggleGroup accountsGroup;
    public Button logoutButton;
    public Button changePasswordButton;
    public TextField newPasswordField;
    public Button requestNewAccountButton;
    public Label newPasswordMessage;
    public Label newUserMessage;
    public Label adminMessage;
    public Button requestNewUserButton;
    public Button accountRequestsButton;
    public Button adminLogoutButton;
    public Button reverseTransactionButton;
    public ComboBox adminAccount_cbox;
    public Button requestUserAccountButton;

    atmRunner atm = new atmRunner();

    // ----- helpers ^___^  ------
    private void clearLoginFields() {
        login_usernameField.setText("");
        login_passwordField.setText("");
        loginMessage.setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    // ----- login events ------
    public void userLogin(ActionEvent actionEvent) {
        if (atm.userLogin(login_usernameField.getText(), login_passwordField.getText())) {
            loginScreen.setVisible(false);
            userScreen.setVisible(true);
            clearLoginFields();
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

