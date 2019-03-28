package atm;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class InterfaceUser {

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

    ObservableList<Account> accounts;
    ObservableList<User> users;



    atmUser atm;


    public void setUpUser(String username) throws IOException{

        atm = new atmUser(username);

        ObservableList<String>   withdrawValues = atm.getWithdrawValues();
        String NetBalance = atm.getNetBalance();
        accounts = atm.getAccounts();
        users = atm.getUsers();


        accounts_cbox.setItems(accounts);
        internalTransferTO_cbox.setItems(accounts);
        internalTransferFROM_cbox.setItems(accounts);
        externalTransfer_cbox.setItems(accounts);
        billPay_cbox.setItems(accounts);
        withdraw_cbox.setItems(withdrawValues);
        netBalance.setText("Net balance: $" + NetBalance);
        recipientUser.setItems(users);

        chequingRadioButton.setUserData(AccountType.CHEQUING);
        savingsRadioButton.setUserData(AccountType.SAVINGS);
        lineRadioButton.setUserData(AccountType.LINEOFCREDIT);
        creditRadioButton.setUserData(AccountType.CREDIT);
        jointRadioButton.setUserData(AccountType.JOINT);
        lotteryAccount.setUserData(AccountType.LOTTERY);


    }

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

    public void logout(ActionEvent actionEvent) throws IOException{

//        atm.logout();
        requestAccountMessage.setText("");
        newPasswordMessage.setText("");
        newPasswordField.clear();
        internalTransferMessage.setText("");
        billPayMessage.setText("");
        externalTransferMessage.setText("");
        withdrawMessage.setText("");
        depositMessage.setText("");
        withdraw_cbox.setValue(null);

        Parent mainScreen = FXMLLoader.load(getClass().getResource("InterfaceLogin.fxml"));
        Scene scene = new Scene(mainScreen);
        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

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

        withdrawAmountField.setText("");
        withdrawMessage.setText("");

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
        double amount = Double.parseDouble(externalTransferAmount.getText());
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
