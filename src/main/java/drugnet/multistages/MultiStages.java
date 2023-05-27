package drugnet.multistages;

import drugnet.RunApp;
import drugnet.alerts.UserAlerts;
import drugnet.models.SystemConfigModel;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MultiStages {
    public static void homePage() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(RunApp.class.getResource("controllers/homepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        Image logo = new Image("G:\\My Drive\\DrugNet\\src\\main\\resources\\drugnet\\cliparts\\DRUGNET.png");
        stage.getIcons().add(logo);
        stage.setResizable(true);
        stage.setTitle("Homepage");
        stage.getScene().getWindow().setOnCloseRequest(WindowEvent -> {
            WindowEvent.consume();
            UserAlerts alerts = new UserAlerts("SIGN OUT", "PLEASE CLOSE THE APPLICATION BY USING THE SIGN OUT BUTTON");
            alerts.informationAlert();
        });
        stage.show();
    }
    public static void loginStage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RunApp.class.getResource("index.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        Image logo = new Image("G:\\My Drive\\DrugNet\\src\\main\\resources\\drugnet\\cliparts\\DRUGNET.png");
        stage.getIcons().add(logo);
        stage.setTitle("Login");
        stage.setScene(scene);stage.setResizable(false);
        stage.show();
    }

    public static void configStage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RunApp.class.getResource("configurations/system_settings.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        Image logo = new Image("G:\\My Drive\\DrugNet\\src\\main\\resources\\drugnet\\cliparts\\DRUGNET.png");
        stage.getIcons().add(logo);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }


}//end of class
