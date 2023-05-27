package drugnet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class RunApp extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RunApp.class.getResource("index.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Image logo = new Image("G:\\My Drive\\DrugNet\\src\\main\\resources\\drugnet\\cliparts\\DRUGNET.png");
        stage.getIcons().add(logo);
        stage.setTitle("Login");
        stage.setScene(scene);stage.setResizable(false);
        stage.show();
    }
    public static void main() {
        launch();
    }
}