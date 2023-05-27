package drugnet.controllers;

import drugnet.RunApp;
import drugnet.alerts.UserAlerts;
import drugnet.alerts.UserNotification;
import drugnet.models.MainModel;
import drugnet.models.SystemConfigModel;
import drugnet.special_methods.SpecialMethods;
import io.github.palexdev.materialfx.controls.MFXButton;
import drugnet.multistages.MultiStages;
import io.github.palexdev.materialfx.enums.NotificationPos;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePage extends MainModel implements Initializable {
    UserAlerts alerts;
    SpecialMethods SPECIAL_METHODS = new SpecialMethods();
    static String staticActiveUser;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setStoreInformationFields();
        activeUserLabel.setText(staticActiveUser);
        try {
            SPECIAL_METHODS.FlipView("controllers/dashboard.fxml", borderPane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /********************************************************************************************************
     ********************** FXML FILE EJECTIONS.
     ********************************************************************************************************/
    @FXML private BorderPane borderPane;
    @FXML private VBox sideBarSlider;
    @FXML private ImageView logoDisplay;
    @FXML private Button handburger;
    @FXML private Pane signoutPane;
    @FXML private Label activeUserLabel, logoNameLabel;
    @FXML private MFXButton dashboardButton, salesButton, systemConfigurationButton, humanResourceButton, reportsButton;
    @FXML private MFXButton stockAdjustmentButton, addMedicineButton, messageBoxButton, accountsButton, runTestButton;



    /********************************************************************************************************
     ********************** TRUE OR FALSE STATEMENTS
     ********************************************************************************************************/




    /********************************************************************************************************
     ********************** IMPLEMENTATION OF OTHER METHODS.
     ********************************************************************************************************/
   
    void transition(Node yourNode, double setToX, double setFromY) {
        TranslateTransition transition = new TranslateTransition(Duration.millis(200), yourNode);
        transition.setToX(setToX);
        transition.setFromY(setFromY);
        transition.setAutoReverse(true);
        transition.play();
    }
    void setStoreInformationFields() {
        logoNameLabel.setText(getStoreInfo().get(0).toString());
        String logo = getStoreInfo().get(4).toString();
        SPECIAL_METHODS.setLogoImage(logo, logoDisplay);
    }




    /********************************************************************************************************
     ********************** Menu Buttons
     ********************************************************************************************************/
    @FXML void dashboardButtonClicked() throws IOException {
        SPECIAL_METHODS.FlipView("controllers/dashboard.fxml", borderPane);
    }
    @FXML void reportsButtonClicked() throws IOException {
        SPECIAL_METHODS.FlipView("controllers/reports.fxml", borderPane);
    }
    @FXML void testButtonClicked() throws IOException {
        SPECIAL_METHODS.FlipView("controllers/runTests.fxml", borderPane);
    }
    @FXML void accountsButtonClicked() throws IOException {
        SPECIAL_METHODS.FlipView("controllers/accounts.fxml", borderPane);
    }
    @FXML void salesButtonClicked() throws IOException {
        SPECIAL_METHODS.FlipView("controllers/sales.fxml", borderPane);
    }
    @FXML void messageBoxButtonClicked() throws IOException {
        SPECIAL_METHODS.FlipView("controllers/messageBox.fxml", borderPane);
    }
    @FXML void addMedicineButtonClicked() throws IOException {
        SPECIAL_METHODS.FlipView("controllers/addMedicine.fxml", borderPane);
    }
    @FXML void stockAdjustmentButtonClicked() throws IOException {
        SPECIAL_METHODS.FlipView("controllers/stockAdjustment.fxml", borderPane);
    }
    @FXML void humanResourceButtonClicked() throws IOException {
        SPECIAL_METHODS.FlipView("controllers/humanResources.fxml", borderPane);
    }
    @FXML void configurattionButtonClicked() throws IOException {
        SPECIAL_METHODS.FlipView("configurations/system_settings.fxml", borderPane);
    }

    /********************************************************************************************************
     ********************** IMPLEMENTATION OF ACTION EVENT METHODS....
     ********************************************************************************************************/
    @FXML void handBurgerClicked() {
        if (sideBarSlider.getTranslateY() == 0) {
            transition(sideBarSlider, -135, -1);
        } else {
            transition(sideBarSlider, 0, 0);
        }
    }
    @FXML public void signoutPaneClicked() throws IOException {
        alerts = new UserAlerts("SIGN OUT", "DO YOU WANT TO SIGN OUT NOW?", "confirm to sign out else CANCEL to abort.");
        if (alerts.confirmationAlert()) {
            signoutPane.getScene().getWindow().hide();
            MultiStages.loginStage();
        }
    }












}//end of class