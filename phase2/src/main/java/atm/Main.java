package atm;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static java.time.temporal.ChronoUnit.MINUTES;
import static java.time.temporal.ChronoUnit.SECONDS;

public class Main extends Application {


    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Filename f = new Filename();
        Parent root = FXMLLoader.load(getClass().getResource("InterfaceLogin.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setTitle("ATM");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
