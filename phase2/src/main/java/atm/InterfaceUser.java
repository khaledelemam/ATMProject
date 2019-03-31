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

/** View class for functionality of users in UI. */
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
            creditRadioButton, lineRadioButton, jointRadioButton, lotteryAccount;
    public TextField shareAccountField;
    public Button requestAccountButton;
    public Label requestAccountMessage;
    public Button logoutButton;
    public Label date;

    // accounts
    public ComboBox<Account> accounts_cbox;
    public Button accounts_showAccountsButton;
    public Label accounts_infoArea;
    public Label netBalance;

    // transfers
    public ComboBox<Account> externalTransfer_cbox, internalTransferTO_cbox,
            internalTransferFROM_cbox, billPay_cbox;
    public ComboBox<User> recipientUser;
    public Button internalTransferButton, externalTransferButton, payBillButton;
    public Label internalTransferMessage, billPayMessage, externalTransferMessage;
    public TextField externalTransferAmount, internalTransferAmount, billPayAmount;
    public ComboBox<String> withdraw_cbox;

    // withdraw/deposit
    public Button depositButton, withdrawButton;
    public TextField depositAmountField;
    public Label depositMessage, withdrawMessage;
    // end

    atmUser atm;


    public void setUpUser(String username) throws IOException {

        atm = new atmUser(username);

        ObservableList<String> withdrawValues = atm.getWithdrawValues();
        String NetBalance = atm.getNetBalance();
        ObservableList<User> users = atm.getUsers();
        ObservableList<Account> accounts = atm.getAccounts();
        Time time = new Time();


        date.setText(time.toString());
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
        String newPassword = newPasswordField.getText();
        if (newPassword.matches("\\w+")) {
            atm.changePassword(newPassword);
            newPasswordMessage.setText("Password changed");
        } else {
            newPasswordMessage.setText("Enter letters or numbers");
        }
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

    public void showAccountInfo(ActionEvent actionEvent) {
        if (accounts_cbox.getSelectionModel().getSelectedItem() != null) {
            accounts_infoArea.setText(atm.viewAccountInfo(accounts_cbox.getSelectionModel().getSelectedItem()));
        } else {
            accounts_infoArea.setText("Please choose an account.");
        }

        netBalance.setText("Net balance: $" + atm.getNetBalance());
    }

    public void deposit(ActionEvent actionEvent) {
        if (checkInput(depositAmountField.getText())){

            double amount = Double.parseDouble(depositAmountField.getText());
            depositMessage.setText(atm.deposit(amount));
        }
        else{
            depositMessage.setText("Please enter a number.");
        }
        depositAmountField.setText("");

    }



    public void withdraw(ActionEvent actionEvent) {
        withdrawMessage.setText(atm.withdraw(Double.valueOf((withdraw_cbox.getSelectionModel().getSelectedItem()))));
    }

    public void internalTransfer(ActionEvent actionEvent) {

        Account sender = internalTransferFROM_cbox.getSelectionModel().getSelectedItem();
        Account recipient = internalTransferTO_cbox.getSelectionModel().getSelectedItem();

        if (checkInput(internalTransferAmount.getText())){

            double amount = Double.parseDouble(internalTransferAmount.getText());
            if (recipient == sender) {
                internalTransferMessage.setText("Can't transfer to the same account.");
            } else if (sender instanceof CreditCard) {
                internalTransferMessage.setText("Can't transfer out of a credit card account.");
            } else {
                internalTransferMessage.setText(atm.internalTransfer(sender, recipient, amount));
            }
        } else{
            internalTransferMessage.setText("Please enter a number.");
        }
        internalTransferAmount.setText("");
    }


    public void externalTransfer(ActionEvent actionEvent) {
        User recipient = recipientUser.getSelectionModel().getSelectedItem();
        Account sender = externalTransfer_cbox.getSelectionModel().getSelectedItem();

        if(checkInput(externalTransferAmount.getText())){
            double amount = Double.parseDouble(externalTransferAmount.getText());
            if (sender instanceof CreditCard) {
                externalTransferMessage.setText("Can't transfer out of a credit card account.");
            } else {
                externalTransferMessage.setText(atm.externalTransfer(recipient, amount, sender));
            }
        }
        else{
            externalTransferMessage.setText("Please enter a number.");
        }
        externalTransferAmount.setText("");

    }

    public void payBill(ActionEvent actionEvent) {
        if(checkInput(billPayAmount.getText())){
            Account sender = billPay_cbox.getSelectionModel().getSelectedItem();
            double amount = Double.parseDouble(billPayAmount.getText());
            billPayMessage.setText(atm.payBill(sender, amount));
        }
        else{
            billPayMessage.setText("Please enter a number.");
        }
        billPayAmount.setText("");

    }


    public void logout(ActionEvent actionEvent) throws IOException{
        Filename f = new Filename();
        Parent mainScreen = FXMLLoader.load(getClass().getResource(f.getLoginFile()));
        Scene scene = new Scene(mainScreen);
        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }


    /** Helper method to check if amount input is in correct decimal format. */
    private boolean checkInput(String fieldInput) {
        return fieldInput.matches("^\\d+\\.?\\d{0,2}");
    }


}
