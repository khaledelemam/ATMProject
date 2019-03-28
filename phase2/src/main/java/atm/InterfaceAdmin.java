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

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
    private  ObservableList<String> bills;

    atmController atm = new atmController();


    public InterfaceAdmin () throws IOException{
        CashManager cm = new CashManager();
        alerts = cm.showAlerts();
        bills = atm.getBills();

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        adminUser_cbox.setItems(atm.getUsersReverse());
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
        atm.advanceDate(Integer.parseInt(daysField.getText()));
        daysField.setText("");
    }

    public void adminLogout(ActionEvent actionEvent) throws IOException {

        Parent mainScreen = FXMLLoader.load(getClass().getResource("Interface.fxml"));
        Scene scene = new Scene(mainScreen);
        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();


    }

    public void addBills(ActionEvent actionEvent) throws IOException {

        CashManager cm = new CashManager();
        int amount = Integer.parseInt(addBillsAmountField.getText());
        int bill = Integer.parseInt(addBills_cbox.getSelectionModel().getSelectedItem());
        adminCashMessage.setText(atm.addBills(amount,bill));
        adminAlertMessage.setText(cm.showAlerts());

        adminCashMessage.setText("");
        addBillsAmountField.setText("");


    }

    public void showUserAccounts(ActionEvent actionEvent) {
//       reverse transactions does not show users until a user logs in first

        atm.setUser(adminUser_cbox.getSelectionModel().getSelectedItem());
        adminAccount_cbox.setItems(FXCollections.observableArrayList(atm.getAccounts()));
    }

    public void reverseLastTransaction(ActionEvent actionEvent) {

        transactionMessage.setText(atm.reverseTransaction(adminAccount_cbox.getSelectionModel().getSelectedItem()));
        atm.setUser(null);
        transactionMessage.setText("");

    }





}
