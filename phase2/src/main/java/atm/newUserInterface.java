package atm;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class newUserInterface {


    public AnchorPane newUserScreen;
    public Button requestUserAccountButton;
    public TextField new_usernameField;
    public Button backButton;
    public Label newUserMessage;

    atmController atm = new atmController();

    // ----- new user events -----
    public void goBack(ActionEvent actionEvent)  throws IOException{
        newUserMessage.setText("");
        new_usernameField.clear();

//        Window window = backButton.getScene().getWindow();
//        if (window instanceof Stage) {
//            Parent root = FXMLLoader.load(getClass().getResource("Interface.fxml"));
//            Scene scene = new Scene(root);
//            Stage stage = (Stage) window;
//            stage.setScene(scene);
//            stage.show();
//        }

        Parent newUserScreen = FXMLLoader.load(getClass().getResource("Interface.fxml"));
        Scene scene = new Scene(newUserScreen);
        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }

    public void requestUserAccount (ActionEvent actionEvent) {
        // TODO: use regex to control user input amount format
        newUserMessage.setText(atm.newUserRequest(new_usernameField.getText()));
    }

}
