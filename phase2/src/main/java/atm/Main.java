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
    public void start(Stage primaryStage) throws IOException {
        Stage stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("Interface.fxml"));
        stage.setTitle("ATM");

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
