package atm.View;

import atm.Model.Filename;
import atm.Model.Time;
import atm.Model.admin.BankManager;
import atm.Controllers.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** View class for logging into UI. */
public class InterfaceLogin implements Initializable {

    // ---- login ----
    public AnchorPane loginScreen;
    public TextField login_passwordField;
    public Button loginButton;
    public Button newAccountButton;
    public Button adminButton;
    public TextField login_usernameField;
    public Label loginMessage;
    public Label date;

    // initialize ATM
    LoginController atm = new LoginController(new BankManager());
    private Filename f = new Filename();
    private String inputWarning = "Invalid username or password.";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Time time = new Time();
        date.setText(time.toString());
    }

    // ----- login events ------
    public void userLogin(ActionEvent actionEvent) throws IOException {
        if (checkInput(login_usernameField.getText()) && checkInput(login_passwordField.getText())) {
            if (atm.userLogin(login_usernameField.getText(), login_passwordField.getText())) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(f.getUserFile()));
                Parent UserScreen = loader.load();
                InterfaceUser controller = loader.getController();
                controller.setUpUser(login_usernameField.getText());

                Scene scene = new Scene(UserScreen);
                Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();

            } else {
                loginMessage.setText(inputWarning);
            }
        } else {
            loginMessage.setText(inputWarning);
        }
        clearLoginFields();
    }



    public void adminLogin(ActionEvent actionEvent) throws IOException {
        if (checkInput(login_usernameField.getText()) && checkInput(login_passwordField.getText())) {
            if (atm.adminCheck(login_usernameField.getText(), login_passwordField.getText())) {

                Parent adminScreen = FXMLLoader.load(getClass().getResource(f.getAdminFile()));
                Scene scene = new Scene(adminScreen);
                Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
            } else if (atm.bankTellerCheck(login_usernameField.getText(), login_passwordField.getText())) {
                // do bankTeller interface stuff

                Parent internScreen = FXMLLoader.load(getClass().getResource(f.getBankInternFile()));
                Scene scene = new Scene(internScreen);
                Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();

            } else {
                loginMessage.setText("Admin access denied");
            }
        } else {
            loginMessage.setText(inputWarning);
        }
        clearLoginFields();
    }

    public void newUser(ActionEvent actionEvent) throws IOException {

        Parent newUserScreen = FXMLLoader.load(getClass().getResource(f.getNewUserFile()));
        Scene scene = new Scene(newUserScreen);
        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }

    // ----- helpers ^___^  ------

    private void clearLoginFields() {
        login_usernameField.setText("");
        login_passwordField.setText("");
    }

    /** Helper method to check if text input is in correct format. */
    private boolean checkInput(String fieldInput) {
        return fieldInput.matches("^\\w+");
    }



}
