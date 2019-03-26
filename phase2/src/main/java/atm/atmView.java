package atm;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class atmView implements Initializable {

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
    public ComboBox<Account> adminAccount_cbox;
    public Button showUserAccountsButton;
    public Label transactionMessage;


    // ---- user ----
    public AnchorPane userScreen;
    public Tab accountTab;

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
    public ComboBox<Account> accounts_cbox;
    public Button accounts_showAccountsButton;
    public Label accounts_infoArea;
    public Label netBalance;

    // transfers
    public ComboBox<Account>  externalTransfer_cbox, internalTransferTO_cbox,
            internalTransferFROM_cbox,  billPay_cbox;
    public ComboBox<User> recipientUser;
    public Button internalTransferButton, externalTransferButton, payBillButton;
    public Label internalTransferMessage, billPayMessage, externalTransferMessage;
    public TextField externalTransferAmount, internalTransferAmount, billPayAmount;
    public ComboBox<String> withdraw_cbox;

    // withdraw/deposit
    public Button depositButton, withdrawButton;
    public TextField depositAmountField;
    public TextField withdrawAmountField;
    public Label depositMessage, withdrawMessage;
    public RadioButton lotteryAccount;
    // end

    // initialize ATM
    atmController atm = new atmController();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // time stuff
        atm.setTimeInitial();
        // set radio buttons
        chequingRadioButton.setUserData(AccountType.CHEQUING);
        savingsRadioButton.setUserData(AccountType.SAVINGS);
        lineRadioButton.setUserData(AccountType.LINEOFCREDIT);
        creditRadioButton.setUserData(AccountType.CREDIT);
        jointRadioButton.setUserData(AccountType.JOINT);
        lotteryAccount.setUserData(AccountType.LOTTERY);
    }

    // ----- helpers ^___^  ------

    private void clearLoginFields() {
        login_usernameField.setText("");
        login_passwordField.setText("");
        loginMessage.setText("");
    }

    private void userSessionSetUp() throws IOException{
        accounts_cbox.setItems(atm.getAccounts());
        internalTransferTO_cbox.setItems(atm.getAccounts());
        internalTransferFROM_cbox.setItems(atm.getAccounts());
        externalTransfer_cbox.setItems(atm.getAccounts());
        billPay_cbox.setItems(atm.getAccounts());
        // withdraw needs IOException
        withdraw_cbox.setItems(atm.getWithdrawValues());


        // TODO: have these be updated when their tab is clicked on instead!
        netBalance.setText("Net balance: $" + atm.getNetBalance());
        // TODO: should this be a text field instead actually?
        recipientUser.setItems(atm.getUsers());
    }



    // ----- login events ------
    public void userLogin(ActionEvent actionEvent) throws IOException{
        // TODO: use regex to control user input amount format
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
//        loginScreen.setVisible(false);
//        newUserScreen.setVisible(true);
        Window window = newAccountButton.getScene().getWindow();
        if (window instanceof Stage) {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("interfaceNewUser.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene scene = new Scene(root);
            Stage stage = (Stage) window;
            stage.setScene(scene);
            stage.show();
        }
    }

    public void adminLogin(ActionEvent actionEvent) throws IOException {
        // TODO: use regex to control user input amount format
        if (atm.adminCheck(login_usernameField.getText(), login_passwordField.getText())) {
            clearLoginFields();
            adminScreen.setVisible(true);
            loginScreen.setVisible(false);
            // TODO: have the cash alert return a string instead of printing
            //adminAlertMessage.setText("<Alert message from cashmanager goes here>");
            CashManager cm = new CashManager();
            adminAlertMessage.setText(cm.showAlerts());

            // TODO: have this updated when their tab is looked at instead!
            adminUser_cbox.setItems(atm.getUsersReverse());
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
        // TODO: use regex to control user input amount format
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

    public void advanceTime(ActionEvent actionEvent) {
        atm.advanceDate(Integer.parseInt(daysField.getText()));
        daysField.setText("");
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
//       reverse transactions does not show users until a user logs in first

        atm.setUser(adminUser_cbox.getSelectionModel().getSelectedItem());
        adminAccount_cbox.setItems(FXCollections.observableArrayList(atm.getAccounts()));
    }

    public void reverseLastTransaction(ActionEvent actionEvent) {
        transactionMessage.setText(atm.reverseTransaction(accounts_cbox.getSelectionModel().getSelectedItem()));
        atm.setUser((User) null);
    }







    // ----- user events -----

    public void changePassword(ActionEvent actionEvent) {
        // TODO: use regex to control user input amount format
        atm.changePassword(newPasswordField.getText());
        newPasswordMessage.setText("Password changed");
    }

    public void requestAccount(ActionEvent actionEvent) {
        // TODO: better way to do this?
        AccountType account = (AccountType) newAccountsGroup.getSelectedToggle().getUserData();
        requestAccountMessage.setText(atm.requestNewAccount(account, shareAccountField.getText()));
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
        withdrawMessage.setText("");
        depositMessage.setText("");

        withdraw_cbox.setValue(null);

        userScreen.setVisible(false);
        loginScreen.setVisible(true);

    }

    public void showAccountInfo(ActionEvent actionEvent) {
        accounts_infoArea.setText(atm.viewAccountInfo(accounts_cbox.getSelectionModel().getSelectedItem()));
        netBalance.setText("Net balance: $" + atm.getNetBalance());
    }

    public void deposit(ActionEvent actionEvent) {
        // TODO: use regex to control user input amount format
        depositMessage.setText(atm.deposit(Double.parseDouble(depositAmountField.getText())));
        depositAmountField.setText("");
    }


    public void withdraw(ActionEvent actionEvent) {
        withdrawMessage.setText(atm.withdraw((Double.valueOf(withdraw_cbox.getSelectionModel().getSelectedItem()))));
//        withdrawAmountField.setText("");

    }

    public void internalTransfer(ActionEvent actionEvent) {
        // TODO: use regex to control user input amount format
        Account sender = internalTransferFROM_cbox.getSelectionModel().getSelectedItem();
        double amount = Double.parseDouble(internalTransferAmount.getText());
        Account recipient = internalTransferTO_cbox.getSelectionModel().getSelectedItem();
        internalTransferMessage.setText(atm.internalTransfer(sender, recipient, amount));
    }

    public void externalTransfer(ActionEvent actionEvent) {
        // TODO: use regex to control user input amount format
        User recipient = recipientUser.getSelectionModel().getSelectedItem();
        Double amount = Double.parseDouble(externalTransferAmount.getText());
        Account sender = externalTransfer_cbox.getSelectionModel().getSelectedItem();
        externalTransferMessage.setText(atm.externalTransfer(recipient, amount, sender));
        externalTransferAmount.setText("");

    }

    public void payBill(ActionEvent actionEvent) {
        // TODO: use regex to control user input amount format
        Account sender = billPay_cbox.getSelectionModel().getSelectedItem();
        Double amount = Double.parseDouble(billPayAmount.getText());
        billPayMessage.setText(atm.payBill(sender, amount));
    }
}
