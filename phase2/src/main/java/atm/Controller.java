package atm;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public AnchorPane loginScreen;
    public PasswordField passwordField;
    public Button requestButton;
    public AnchorPane newUserScreen;
    public Button backButton;
    public TextField new_usernameField;
    public Button loginButton;
    public Button newAccountButton;
    public Button adminButton;
    public TextField login_usernameField;
    public Label loginMessage;
    public PasswordField login_passwordField;

    atmRunner atm = new atmRunner();

    // ----- helper methods ------
    private void emptyFields() {
        login_usernameField.setText("");
        login_passwordField.setText("");
        loginMessage.setVisible(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    // ----- loginScreen events ------
    public void userLogin(ActionEvent event) {
        if (atm.userLogin(login_usernameField.getText(), login_passwordField.getText())) {
            // TODO: change scene to user menu
            emptyFields();
        }
    }

    public void newUser(ActionEvent event) {
        loginScreen.setVisible(false);
        newUserScreen.setVisible(true);
    }

    public void adminLogin(ActionEvent actionEvent) {
    }

    // ----- new user events -----
    public void goBack(ActionEvent actionEvent) {
        newUserScreen.setVisible(false);
        loginScreen.setVisible(true);
    }

    public void requestAccount(ActionEvent actionEvent) {
    }

    // ----- admin events -----

    // ----- user events -----
}
