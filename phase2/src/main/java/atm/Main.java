package atm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage loginStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("loginInterface.fxml"));
        loginStage.setTitle("ATM");
        loginStage.setScene(new Scene(root));
        loginStage.sizeToScene();
        loginStage.show();

    }
}
