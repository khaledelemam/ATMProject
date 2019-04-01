package atm.View;

import atm.Controllers.NewUsersController;
import atm.Model.Filename;
import atm.Model.admin.BankManager;
import atm.Controllers.LoginController;
import atm.Model.users.UserType;
import javafx.collections.FXCollections;
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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** View class for creating new user in UI. */
public class InterfaceNewUser implements Initializable {


    public AnchorPane newUserScreen;
    public Button requestUserAccountButton;
    public TextField new_usernameField;
    public Button backButton;
    public Label newUserMessage;
    public ComboBox<UserType> UserType_cbox;

    NewUsersController atm = new NewUsersController(new BankManager());

    // ----- new user events -----


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserType[] types = UserType.values();
        UserType_cbox.setItems(FXCollections.observableArrayList(types));
    }

    public void goBack(ActionEvent actionEvent)  throws IOException{
        newUserMessage.setText("");
        new_usernameField.clear();

        Filename f = new Filename();
        Parent newUserScreen = FXMLLoader.load(getClass().getResource(f.getLoginFile()));
        Scene scene = new Scene(newUserScreen);
        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }

    public void requestUserAccount (ActionEvent actionEvent) {
        // TODO: use regex to control user input amount format
        if (UserType_cbox.getSelectionModel().getSelectedItem() != null) {
            newUserMessage.setText(atm.newUserRequest(new_usernameField.getText(), UserType_cbox.getSelectionModel().getSelectedItem()));
        }
        else{
            newUserMessage.setText("Please pick a user type.");
        }
    }

}
