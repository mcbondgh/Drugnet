package drugnet.special_methods;

import drugnet.RunApp;
import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;

public class SpecialMethods {

    //TTHIS METHOD WHEN INVOKED SHALL TAKE THE NAME OF THE IMAGE AND THEN DISPLAY IT TO THE IMAGE-VIEW...
    public void setLogoImage(String imageName, ImageView imageViewName) {
        File filePath = new File("C:\\Users\\Druglord\\Documents\\drugnet\\" + imageName);
        Image getImage = new Image(filePath.toString());
        imageViewName.setImage(getImage);
    }

    public void FlipView(String fxmlFileName, BorderPane borderPane) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RunApp.class.getResource(fxmlFileName));
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500),borderPane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1.0);
        borderPane.setCenter(fxmlLoader.load());
        fadeTransition.play();
    }


}//end of class...
