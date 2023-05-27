package drugnet.alerts;

import javafx.scene.control.*;
import javafx.scene.control.ButtonType;

public class UserAlerts {

    private String title, header, content;

    public UserAlerts(String title, String header, String content) {
        this.title = title;
        this.header = header;
        this.content = content;
    }

    public UserAlerts(String alertTitle, String alertHeader) {
        this.title = alertTitle;
        this.header = alertHeader;
    }

    public boolean confirmationAlert() {
        boolean flag = false;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, content);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.getButtonTypes().add(ButtonType.YES);
        alert.getButtonTypes().remove(ButtonType.OK);
        if (alert.showAndWait().get().equals(ButtonType.YES)) {
            flag = true;
        }
        return flag;
    }

    public void warningAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void informationAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void errorAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
