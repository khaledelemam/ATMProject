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


        setTimeInitial();

        primaryStage.setTitle("ATM");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setTimeInitial() {

        Time date;

        LocalTime time = ZonedDateTime.now().toLocalTime().truncatedTo(MINUTES);
        System.out.println(time.toString());
        LocalTime midnight = LocalTime.MAX;
        long terminate = SECONDS.between(time, midnight);
        date = new Time(1);

        SavingsAccount savings = new SavingsAccount();

        if (date.date.getDay() == 1) {
            savings.applyInterest(1);
        }
        Executors.newSingleThreadScheduledExecutor().schedule(Platform::exit, terminate, TimeUnit.SECONDS);
        Executors.newSingleThreadScheduledExecutor().schedule(() -> System.exit(0), terminate, TimeUnit.SECONDS);
    }
}
