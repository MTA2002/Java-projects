package HMS_Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class AdminManagementController {
    Hospital_Administrator hospital_administrator = new Hospital_Administrator();

    @FXML
    void cancelOnAction(ActionEvent event) {
        try {
            Hospital_Administrator.switcher = 'A';
            hospital_administrator.switchScene("HomeScene.fxml");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    void manageDoctorsOnAction(ActionEvent event) {
        try {
            Hospital_Administrator.switcher = 'D';
            hospital_administrator.switchScene("doctorManagementScene.fxml");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    void manageReceptionistsOnAction(ActionEvent event) {
        try {
            Hospital_Administrator.switcher = 'R';
            hospital_administrator.switchScene("ReceptionistManagementScene.fxml");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
