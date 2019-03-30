package atm;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/** View class for logging into UI. */
public class InterfaceLogin{

    // ---- login ----
    public AnchorPane loginScreen;
    public TextField login_passwordField;
    public Button loginButton;
    public Button newAccountButton;
    public Button adminButton;
    public TextField login_usernameField;
    public Label loginMessage;

    // initialize ATM
    atmLogin atm = new atmLogin();


    // ----- login events ------
    public void userLogin(ActionEvent actionEvent) throws IOException{
        // TODO: use regex to control user input amount format
        if (atm.userLogin(login_usernameField.getText(), login_passwordField.getText())) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceUser.fxml"));
            Parent UserScreen = loader.load();
            InterfaceUser controller = loader.getController();
            controller.setUpUser(login_usernameField.getText());

            Scene scene = new Scene(UserScreen);
            Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();

        }
        else {
            clearLoginFields();
            loginMessage.setText("Invalid username or password.");
        }
    }



    public void adminLogin(ActionEvent actionEvent) throws IOException {
        // TODO: use regex to control user input amount format
        if (atm.adminCheck(login_usernameField.getText(), login_passwordField.getText())) {
            Parent adminScreen = FXMLLoader.load(getClass().getResource("InterfaceAdmin.fxml"));
            Scene scene = new Scene(adminScreen);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }
        else if (atm.bankTellerCheck(login_usernameField.getText(), login_passwordField.getText())){
            // do bankTeller interface stuff
        }
        else {
            clearLoginFields();
            loginMessage.setText("Admin access denied");
        }
    }

    public void newUser(ActionEvent actionEvent) throws IOException {

        Parent newUserScreen = FXMLLoader.load(getClass().getResource("InterfaceNewUser.fxml"));
        Scene scene = new Scene(newUserScreen);
        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }

    // ----- helpers ^___^  ------

    private void clearLoginFields() {
        login_usernameField.setText("");
        login_passwordField.setText("");
        loginMessage.setText("");
    }



}
