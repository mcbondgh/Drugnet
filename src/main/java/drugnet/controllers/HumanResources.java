package drugnet.controllers;

import drugnet.models.HumanResourceModel;
import drugnet.special_methods.SpecialMethods;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HumanResources extends HumanResourceModel implements Initializable {
    SpecialMethods SPECIAL_METHODS = new SpecialMethods();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            SPECIAL_METHODS.FlipView("views/employeesview.fxml", borderPane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*******************************************************************************************************************
     **************************** FXML NODE EJECTIONS
     ******************************************************************************************************************/
    @FXML private MFXButton employeesViewButton, userViewButton, userRolesViewButton, userLogsViewButton, payRollViewButton;
    @FXML private BorderPane borderPane;


    /*******************************************************************************************************************
     **************************** IMPLEMENTATION OF OTHER METHODS.
     ******************************************************************************************************************/


    /*******************************************************************************************************************
     **************************** IMPLEMENTATION OF TRUE OR FALSE STATEMENTS.
     ******************************************************************************************************************/



    /*******************************************************************************************************************
     **************************** ACTION EVENT METHODS IMPLEMENTATION...
     ******************************************************************************************************************/
    @FXML void employeesViewButtonClicked() throws IOException {
        SPECIAL_METHODS.FlipView("views/employeesview.fxml", borderPane);
    }

    
}//end of class
