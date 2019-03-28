package atm;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

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

    atmController atm = new atmController();

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
//        addBills_cbox.setItems(null);



//        userScreen.setVisible(false);
//        loginScreen.setVisible(true);

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
