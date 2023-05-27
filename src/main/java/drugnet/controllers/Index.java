package drugnet.controllers;

import drugnet.models.SystemConfigModel;
import drugnet.special_methods.SpecialMethods;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import drugnet.multistages.MultiStages;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Index extends SystemConfigModel implements Initializable{
    SpecialMethods SPECIAL_METHODS_OBJECT = new SpecialMethods();


    @FXML
    private Button loginButton, closeButton;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML private ImageView logoDisplay;
    @FXML private Label logoNameLabel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String logo = getStoreInfo().get(4).toString();
        SPECIAL_METHODS_OBJECT.setLogoImage(logo, logoDisplay);
        logoNameLabel.setText(getStoreInfo().get(0).toString());
        loginButton.setOnAction(action -> {
            try {
                String providedUsername =  usernameField.getText();
                HomePage.staticActiveUser = usernameField.getText().equals("") ? "Admin" : providedUsername;
                MultiStages.homePage();
                loginButton.getScene().getWindow().hide();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        closeButton.setOnAction(action -> {
            System.exit(0);
        });
    }



    /********************************************************************************************************
     ********************** IMPLEMENTATION OF TRUE OR FALSE STATEMENT.
     ********************************************************************************************************/




    /********************************************************************************************************
     ********************** IMPLEMENTATION OF OTHER METHODS.
     ********************************************************************************************************/




    /********************************************************************************************************
     ********************** IMPLEMENTATION OF ACTION EVENT METHODS....
     ********************************************************************************************************/


}
