package drugnet.controllers;

import com.jfoenix.controls.JFXButton;
import drugnet.fetchedData.EmployeesData;
import drugnet.fetchedData.UsersData;
import drugnet.models.HumanResourceModel;
import drugnet.multistages.MultiStages;
import drugnet.special_methods.SpecialMethods;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyTableView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HumanResources  implements Initializable {
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
     **************************** ACTION EVENT METHODS IMPLEMENTATION...
     ******************************************************************************************************************/
    @FXML void employeesViewButtonClicked() throws IOException {
        SPECIAL_METHODS.FlipView("views/employeesview.fxml", borderPane);
    }
    @FXML void userViewButtonClicked() throws IOException {
        SPECIAL_METHODS.FlipView("views/addusers.fxml", borderPane);
    }


}//end of class
