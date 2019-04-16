package atm.View;

import atm.Model.CashManager;
import atm.Model.Filename;
import atm.Model.users.BankIntern;
import atm.Controllers.BankInternController;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InterfaceBankIntern implements Initializable {

    public Button addBillsButton;
    public Label adminCashMessage;

    public TextField addBillsAmountField;

    public Label adminAlertMessage;
    public ComboBox<String> addBills_cbox;
    public Button LogoutButton;

    private String alerts;
    private  ObservableList<String> bills;



    BankInternController atm = new BankInternController(new BankIntern());


    public InterfaceBankIntern() throws IOException{
        CashManager cm = new CashManager();
        alerts = cm.showAlerts();
        bills = atm.getBills();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        adminAlertMessage.setText(alerts);
        addBills_cbox.setItems(bills);

    }


    public void addBills(ActionEvent actionEvent) throws IOException {

        CashManager cm = new CashManager();
        int amount = Integer.parseInt(addBillsAmountField.getText());
        int bill = Integer.parseInt(addBills_cbox.getSelectionModel().getSelectedItem());
        adminCashMessage.setText(atm.addBills(amount,bill));
        adminAlertMessage.setText(cm.showAlerts());

        addBillsAmountField.setText("");


    }


    public void logout(ActionEvent actionEvent) throws IOException{

        Filename f = new Filename();
        Parent mainScreen = FXMLLoader.load(getClass().getResource(f.getLoginFile()));
        Scene scene = new Scene(mainScreen);
        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();



    }



}
