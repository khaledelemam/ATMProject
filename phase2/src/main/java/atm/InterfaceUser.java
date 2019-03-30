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
            creditRadioButton, lineRadioButton, jointRadioButton, lotteryAccount;;
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
    public Label depositMessage, withdrawMessage;
    // end

    atmUser atm;


    public void setUpUser(String username) throws IOException{

        atm = new atmUser(username);

        ObservableList<String>  withdrawValues = atm.getWithdrawValues();
        String NetBalance = atm.getNetBalance();
        ObservableList<User> users = atm.getUsers();
        ObservableList<Account> accounts = atm.getAccounts();


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
                newPasswordMessage.setText("Must be letter or digit.");
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

    public void hideShareAccount(ActionEvent actionEvent) {
        shareAccountField.setVisible(false);
    }

    public void showAccountInfo(ActionEvent actionEvent) {
        accounts_infoArea.setText(atm.viewAccountInfo(accounts_cbox.getSelectionModel().getSelectedItem()));
        netBalance.setText("Net balance: $" + atm.getNetBalance());
    }

    public void deposit(ActionEvent actionEvent) {
        String amount = depositAmountField.getText();
        if (amount.matches("^\\d+\\.?\\d{0,2}")) {
            depositMessage.setText(atm.deposit(Double.parseDouble(amount)));
            depositAmountField.setText("");
        } else {
            depositMessage.setText("Please enter an amount.");
        }
        }




    public void withdraw(ActionEvent actionEvent) {
        withdrawMessage.setText(atm.withdraw((Double.valueOf(withdraw_cbox.getSelectionModel().getSelectedItem()))));
    }

    public void internalTransfer(ActionEvent actionEvent) {
        Account sender = internalTransferFROM_cbox.getSelectionModel().getSelectedItem();
        String inputAmount = internalTransferAmount.getText();
        if (inputAmount.matches("^\\d+\\.?\\d{0,2}")) {
            double amount = Double.parseDouble(inputAmount);
            Account recipient = internalTransferTO_cbox.getSelectionModel().getSelectedItem();
            if (recipient == sender){
                internalTransferMessage.setText("Can't transfer to the same account.");
            }
            else if (sender instanceof CreditCard){
                internalTransferMessage.setText("Can't transfer out of a credit card account.");
            }
            else {
                internalTransferMessage.setText(atm.internalTransfer(sender, recipient, amount));
                internalTransferAmount.setText("");
            }
        } else {
            internalTransferMessage.setText("Please enter an amount.");
        }
    }


    public void externalTransfer(ActionEvent actionEvent) {
        User recipient = recipientUser.getSelectionModel().getSelectedItem();
        String inputAmount = externalTransferAmount.getText();
        if (inputAmount.matches("^\\d+\\.?\\d{0,2}")) {
            double amount = Double.parseDouble(inputAmount);
            Account sender = externalTransfer_cbox.getSelectionModel().getSelectedItem();
            if (sender instanceof CreditCard){
                externalTransferMessage.setText("Can't transfer out of a credit card account.");
            }
            else {
                externalTransferMessage.setText(atm.externalTransfer(recipient, amount, sender));
                externalTransferAmount.setText("");
            }
        } else {
            externalTransferMessage.setText("Please enter an amount.");
        }

    }

    public void payBill(ActionEvent actionEvent) {
        Account sender = billPay_cbox.getSelectionModel().getSelectedItem();
        String inputAmount = billPayAmount.getText();
        if (inputAmount.matches("^\\d+\\.?\\d{0,2}")) {
            double amount = Double.parseDouble(inputAmount);
            billPayMessage.setText(atm.payBill(sender, amount));
        } else {
            billPayMessage.setText("Please enter an amount.");
        }
    }


    public void logout(ActionEvent actionEvent) throws IOException{

        Parent mainScreen = FXMLLoader.load(getClass().getResource("InterfaceLogin.fxml"));
        Scene scene = new Scene(mainScreen);
        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }





}
