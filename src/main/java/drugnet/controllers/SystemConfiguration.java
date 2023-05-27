package drugnet.controllers;

import drugnet.alerts.UserAlerts;
import drugnet.alerts.UserNotification;
import drugnet.fetchedData.SmsApiData;
import drugnet.models.SystemConfigModel;
import drugnet.special_methods.SpecialMethods;
import drugnet.tableViewData.BrandsTableData;
import drugnet.tableViewData.SuppliersTableData;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyTableView;
import io.github.palexdev.materialfx.utils.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class SystemConfiguration extends SystemConfigModel implements Initializable {
    UserNotification NOTIFICATIONS = new UserNotification();
    UserAlerts ALERTS;
    SpecialMethods SPECIAL_METHODS = new SpecialMethods();
    SmsApiData API_DATA = new SmsApiData();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setStoreInformationFields();
        setSmsApiVariables();
        populateBrandsTable();
        populateSuppliersTable();
        supplierTableItemSelected();
        branTableItemSelected();
    }


    /********************************************************************************************************
     ********************** FXML FILE EJECTIONS.
     ********************************************************************************************************/
    @FXML private TextField storeNameField, storeAddressField, storeEmailAddressField, storeNumberField;
    @FXML private TextField managerNameField, managerNumberField, managerEmailField, logoPathField;
    @FXML private MFXButton browseFileButton, updateInfoButton, addBrandButton, addSupplierButton;
    @FXML private ImageView logoView;
    @FXML private Label fileSizeIndicator;
    @FXML private TextField supplierNameField, digitalAddressField, supplierNumberField,supplierLocationField;
    @FXML private TextField brandNameField, manufacturerField;
    @FXML private TextArea commentsField;
    @FXML TextField apiKeyField, senderIdField;
    @FXML private MFXButton updateMailButton, updateApiButton;




    /********************************************************************************************************
     ********************** TABLE VIEW NODES
     ********************************************************************************************************/
    @FXML private MFXLegacyTableView<SuppliersTableData> suppliersTable;
    @FXML private TableColumn<SuppliersTableData, String> supplierNameColumn;
    @FXML private TableColumn<SuppliersTableData, String> supplierNumberColumn;
    @FXML private TableColumn<SuppliersTableData, String> digitalAddressColumn;
    @FXML private TableColumn<SuppliersTableData, String> locationColumn;
    @FXML private TableColumn<SuppliersTableData, String> supplierCommentColumn;


    @FXML private MFXLegacyTableView<BrandsTableData> brandsTable;
    @FXML private TableColumn<BrandsTableData, Integer> brandIdColumn;
    @FXML private TableColumn<BrandsTableData, String> brandNameColumn;
    @FXML private TableColumn<BrandsTableData, String> manufacturerColumn;


    /********************************************************************************************************
     ********************** TRUE OR FALSE STATEMENTS.
     ********************************************************************************************************/
    boolean isStoreNameEmpty() {
        return storeNameField.getText().isBlank();
    }
    boolean isStoreAddressEmpty() {
        return storeAddressField.getText().isBlank();
    }
    boolean isStoreMobilEmpty() {
        return storeNumberField.getText().isBlank();
    }
    boolean isStoreEmailEmpty() {
        return storeEmailAddressField.getText().isBlank();
    }
    boolean isManagerNameFieldEmpty() {
        return managerNameField.getText().isBlank();
    }
    boolean isManagerEmailEmpty() {
        return managerEmailField.getText().isBlank();
    }
    boolean isManageNumberEmpty() {
        return managerNumberField.getText().isBlank();
    }
    boolean isLogoPathEmpty() { return logoPathField.getText().isBlank();}

    boolean isSupplierNameEmpty() {
        return supplierNameField.getText().isBlank();
    }
    boolean isSupplierNumberEmpty() {
        return supplierNumberField.getText().isBlank();
    }
    boolean isSupplierLocationEmpty() {
        return supplierLocationField.getText().isBlank();
    }
    boolean isSupplierAddressEmpty() {
        return digitalAddressField.getText().isEmpty();
    }
    boolean isBrandNameEmpty() {
        return brandNameField.getText().isEmpty();
    }
    boolean isManufacturerNameEmpty() {
        return manufacturerField.getText().isEmpty();
    }
    boolean isSupplierTableSelected() {
        return suppliersTable.getSelectionModel().getSelectedItems().isEmpty();
    }
    boolean isBrandTableSelected() {
        return brandsTable.getSelectionModel().getSelectedItems().isEmpty();
    }

    /********************************************************************************************************
     ********************** IMPLEMENTATION OF OTHER METHODS.
     ********************************************************************************************************/

    //THIS METHOD WHEN INVOKED SHALL SAVE THE UPLOADED IMAGE INTO THE SPECIFIED DIRECTORY. DIRECTORY;
    void saveImageToFolder() throws IOException {
        try {
            Image selectedImage = logoView.getImage();
            String imageName = logoPathField.getText();
            File filePath = new File("C:\\Users\\Druglord\\Documents\\drugnet\\" + imageName);
            ImageIO.write(SwingFXUtils.fromFXImage(selectedImage, null), "png", filePath);
        } catch (Exception ignored) {}
    }
    void setStoreInformationFields() {
        storeNameField.setText(getStoreInfo().get(0).toString());
        storeAddressField.setText(getStoreInfo().get(1).toString());
        storeNumberField.setText(getStoreInfo().get(2).toString());
        storeEmailAddressField.setText(getStoreInfo().get(3).toString());
        String logo = getStoreInfo().get(4).toString();
        logoPathField.setText(logo);
        SPECIAL_METHODS.setLogoImage(logo, logoView);
        managerNameField.setText(getStoreInfo().get(5).toString());
        managerEmailField.setText(getStoreInfo().get(6).toString());
        managerNumberField.setText(getStoreInfo().get(7).toString());
    }
    void setSmsApiVariables() {
        apiKeyField.setText(getSmsApi().get(1).toString());
        senderIdField.setText(getSmsApi().get(2).toString());
    }
    void refreshSuppliersTable() {
        suppliersTable.getItems().clear();
        populateSuppliersTable();
    }
    void refreshBrandsTable() {
        brandsTable.getItems().clear();
        populateBrandsTable();
    }
    private void populateBrandsTable() {
        brandIdColumn.setCellValueFactory(new PropertyValueFactory<>("brand_id"));
        brandNameColumn.setCellValueFactory(new PropertyValueFactory<>("brandName"));
        manufacturerColumn.setCellValueFactory(new PropertyValueFactory<>("manufacturerName"));
        brandsTable.setItems(getBrandsData());
    }
    private void populateSuppliersTable() {
        supplierNameColumn.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        supplierNumberColumn.setCellValueFactory(new PropertyValueFactory<>("supplierNumber"));
        digitalAddressColumn.setCellValueFactory(new PropertyValueFactory<>("supplierAddress"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("supplierLocation"));
        supplierCommentColumn.setCellValueFactory(new PropertyValueFactory<>("supplierComment"));
        suppliersTable.setItems(getSuppliesData());
    }
    void clearSupplierFields() {
        supplierNameField.clear();
        supplierNumberField.clear();
        supplierLocationField.clear();
        digitalAddressField.clear();
        commentsField.clear();
        addSupplierButton.setDisable(true);
    }
    void clearBrandFields() {
        brandNameField.clear();
        manufacturerField.clear();
        addBrandButton.setDisable(true);
    }


    /********************************************************************************************************
     ********************** INPUT VALIDATION METHODS.
     ********************************************************************************************************/
    @FXML void validateNumberFields(KeyEvent e) {
        try {
            if (!(e.getCode().isDigitKey() || e.getCode().isArrowKey() || e.getCode().equals(KeyCode.BACK_SPACE))) {
                managerNumberField.deletePreviousChar();
                storeNumberField.deletePreviousChar();
                managerNumberField.deleteNextChar();
                storeNumberField.deleteNextChar();
            }
            if (managerNumberField.getLength() > 10 || storeNumberField.getLength() > 10) {
                managerNumberField.deletePreviousChar();
                storeNumberField.deletePreviousChar();
                managerNumberField.deleteNextChar();
                storeNumberField.deleteNextChar();
            }
        } catch (NumberFormatException ignored) {}

    }//end of method...
    @FXML void checkForEmptyInputFields() {
        updateInfoButton.setDisable(isStoreNameEmpty() || isStoreAddressEmpty() || isStoreEmailEmpty() || isStoreMobilEmpty() || isManageNumberEmpty()
        || isManagerNameFieldEmpty() || isManagerEmailEmpty());
    }
    @FXML void validateSupplierNumber(KeyEvent e) {
        try {
            if (!(e.getCode().isDigitKey() || e.getCode().isArrowKey() || e.getCode().equals(KeyCode.BACK_SPACE))) {
                supplierNumberField.deletePreviousChar();
                supplierNumberField.deleteNextChar();
            }
            if (supplierNumberField.getLength() > 10) {
                supplierNumberField.deletePreviousChar();
                supplierNumberField.deleteNextChar();
            }
        } catch (NumberFormatException ignored) {}
    }
    @FXML void mouseMovedInSuppliersTable() {
        addSupplierButton.setDisable(isSupplierNameEmpty() || isSupplierNumberEmpty() || isSupplierLocationEmpty() || isSupplierAddressEmpty());
    }
    @FXML void mouseMovedInBrandsTable() {
        addBrandButton.setDisable(isBrandNameEmpty() || isManufacturerNameEmpty());
    }
    @FXML void checkSupplierNameExist() {
        String supplierName = supplierNameField.getText().toLowerCase().trim().replaceAll("\\s","");
        for (SuppliersTableData dataItem : getSuppliesData()) {
            String name = dataItem.getSupplierName().trim().toLowerCase().replaceAll("\\s", "");
            if (Objects.equals(supplierName, name)) {
                ALERTS = new UserAlerts("SUPPLIER EXIST", "Provided supplier name already exist in list", "please update or enter a unique name");
                ALERTS.informationAlert();
                addSupplierButton.setDisable(true);
            }
        }
    }
    @FXML void validateSenderField() {
        if (senderIdField.getText().length() > 11) {
            senderIdField.deletePreviousChar();
            senderIdField.deleteNextChar();
        }
    }

    @FXML void mouseMovedInSmsPane() {
        updateApiButton.setDisable(apiKeyField.getText().isEmpty() || senderIdField.getText().isEmpty());
    }

    /********************************************************************************************************
     ********************** IMPLEMENTATION OF ACTION EVENT METHODS.
     ********************************************************************************************************/
    @FXML void browseButtonClicked() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Logo");
        fileChooser.getExtensionFilters().add( new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg"));
        File uploadFile = fileChooser.showOpenDialog(null);
        if (uploadFile != null) {
            long fileSize = uploadFile.length();
            long requiredFileSize = ( 100 * 1024);
            if (fileSize > requiredFileSize ) {
               fileSizeIndicator.setVisible(true);
            } else {
                fileSizeIndicator.setVisible(false);
                Image logo = new Image(uploadFile.getAbsolutePath());
                logoView.setImage(logo);
                logoPathField.setText(uploadFile.getName());
            }
        }
    }
    @FXML void updateStoreButtonClicked() throws IOException {
        String storeName = storeNameField.getText();
        String storeAddress = storeAddressField.getText();
        String storeEmail = storeEmailAddressField.getText();
        String storeNumber = storeNumberField.getText();
        String logoName = logoPathField.getText();
        String managerName = managerNameField.getText();
        String managerEmail = managerEmailField.getText();
        String managerNumber = managerNumberField.getText();
        int activeUserId = getUserIdByUsername(HomePage.staticActiveUser);

       ALERTS = new UserAlerts("UPDATE STORE", "DO YOU WISH TO UPDATE STORE INFORMATION?", "please confirm YES to proceed, else CANCEL to abort.");
       if(ALERTS.confirmationAlert()) {
           if(!logoPathField.getText().isBlank()) {
               saveImageToFolder();
           }
           int flag = updateStoreInfo(storeName, storeAddress, storeNumber, storeEmail, logoName, managerName, managerEmail, managerNumber, activeUserId);
           if (flag > 0) {
               NOTIFICATIONS.successNotification("RECORD UPDATED", "You have successfully updated your store information.");
           } else  {
               NOTIFICATIONS.errorNotification("FAILED TO UPDATE", "Store information failed to update, please make sure you fill fields.");
           }

       }
//
    }
    @FXML void supplierButtonClicked() {
        String supplierName = supplierNameField.getText();
        String mobilNumber = supplierNumberField.getText();
        String location = supplierLocationField.getText();
        String address = digitalAddressField.getText();
        String note = commentsField.getText();

        if(addSupplierButton.getText().equals("➕ Add Supplier")) {
            ALERTS = new UserAlerts("SAVE SUPPLIER", "DO YOU AGREE TO ADD THIS SUPPLIER TO YOUR LIST OF SUPPLIERS?", "please confirm YES to add else CANCEL to abort process.");
            if (ALERTS.confirmationAlert()) {
                int flag = saveSupplier(supplierName, mobilNumber, address,location, note);
                if (flag > 0) {
                    NOTIFICATIONS.successNotification("SUPPLIER SAVED", "New Supplier Successfully Added To List.");
                    refreshSuppliersTable();
                    clearSupplierFields();
                } else {
                    NOTIFICATIONS.errorNotification("FAILED TO SAVE", "Failed to add new supplier, please make sure to till all fields");
                }
            }
        }else {
            int supplier_id = 0;
            for(SuppliersTableData  item : getSuppliesData()) {
                String name = suppliersTable.getSelectionModel().getSelectedItem().getSupplierName();
                if (Objects.equals(name, item.getSupplierName())) {
                    supplier_id = item.getSupplier_id();
                    break;
                }
            }
            ALERTS = new UserAlerts("UPDATE SUPPLIER", "DO YOU AGREE TO UPDATE SELECTED SUPPLIER'S DETAILS?", "please confirm YES to add else CANCEL to abort process.");
            if (ALERTS.confirmationAlert()) {
                int flag = updateSupplierInfo(supplier_id, supplierName, mobilNumber, address, location, note);
                if (flag > 0) {
                    NOTIFICATIONS.successNotification("SUPPLIER UPDATED", "Supplier Details Updated Successfully");
                    refreshSuppliersTable();
                    clearSupplierFields();
                } else {
                    NOTIFICATIONS.errorNotification("UPDATE FAILED", "Failed to update selected supplier, please try again or contact system admin.");
                }
            }
        }
    }
    @FXML void brandButtonClicked() {
        String brandName = brandNameField.getText();
        String manufacturer = manufacturerField.getText();
        if (addBrandButton.getText().equals("➕ Add Supplier")) {
            ALERTS = new UserAlerts("SAVE BRAND", "DO YOU AGREE TO ADD THIS BRAND TO YOUR LIST OF BRANDS?", "please confirm YES to add else CANCEL to abort process.");
            if (ALERTS.confirmationAlert())  {
                int flag = saveBrand(brandName, manufacturer);
                if (flag > 0) {
                    NOTIFICATIONS.successNotification("BRAND SAVED", "New Brand Successfully Added To List.");
                    refreshBrandsTable();
                    clearBrandFields();
                }
            }
        } else {
            int brandId = 0;
            for (BrandsTableData dataItems : brandsTable.getSelectionModel().getSelectedItems()) {
                String name = brandsTable.getSelectionModel().getSelectedItem().getBrandName();
                if (Objects.equals(name, dataItems.getBrandName())) {
                    brandId = dataItems.getBrand_id();
                    break;
                }
            }
            ALERTS = new UserAlerts("UPDATE BRAND", "DO YOU AGREE TO UPDATE SELECTED BRAND?", "please confirm YES to add else CANCEL to abort process.");
            if (ALERTS.confirmationAlert())  {
                int flag = updateBrandInfo(brandId, brandName, manufacturer);
                if (flag > 0) {
                    NOTIFICATIONS.successNotification("BRAND UPDATED", "Selected brand successfully updated.");
                    refreshBrandsTable();
                    clearBrandFields();
                }
            }
        }
    }
    @FXML void supplierTableItemSelected() {
        suppliersTable.setOnMouseClicked(mouseEvent -> {
            if(!isSupplierTableSelected()) {
                for (SuppliersTableData item : suppliersTable.getSelectionModel().getSelectedItems()) {
                    supplierNameField.setText(item.getSupplierName());
                    supplierNumberField.setText(item.getSupplierNumber());
                    supplierLocationField.setText(item.getSupplierLocation());
                    digitalAddressField.setText(item.getSupplierAddress());
                    commentsField.setText(item.getSupplierComment());
                    addSupplierButton.setText("➕Update Supplier ");
                    break;
                }
            } else {
                addSupplierButton.setText("➕Add Supplier ");
            }
        });
    }
    @FXML void branTableItemSelected() {
        brandsTable.setOnMouseClicked(mouseEvent -> {
            if(!isBrandTableSelected()) {
                for (BrandsTableData dataItems : brandsTable.getSelectionModel().getSelectedItems()) {
                    brandNameField.setText(dataItems.getBrandName());
                    manufacturerField.setText(dataItems.getManufacturerName());
                    addBrandButton.setText("➕ Update Supplier");
                    break;
                }
            }
        });
    }
    @FXML void updateApiButtonClicked() {
        String api_key = apiKeyField.getText();
        String sender_id = senderIdField.getText();
        ALERTS = new UserAlerts("UPDATE API", "ARE YOU SURE YOU WISH TO UPDATE SMS API? PLEASE BE SURE BEFORE YOU UPDATE", "you have requested to update your api variables, this changes may affect your sms behaviour.");
        if (ALERTS.confirmationAlert()) {
            if (updateSmsApi(api_key, sender_id) > 0) {
                NOTIFICATIONS.successNotification("UPDATE SUCCESSFUL", "You have successfully updated your sms API. Change are effected immediately.");
            }
        }
    }
    @FXML void updateEmailButtonClicked() {

    }
}//END OF CLASS...
