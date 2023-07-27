package HMS_Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HomeSceneController {
    Hospital_Administrator hospitalAdministrator = new Hospital_Administrator();
    @FXML
    void adminSideOnAction(ActionEvent event) throws Exception {
        try {
            Hospital_Administrator.switcher = 'A';
            hospitalAdministrator.switchScene("login.fxml");
        } catch (Exception e) {
            System.out.println(e);
        }


    }

    @FXML
    void doctorSideOnAction(ActionEvent event) {
        try {
            Hospital_Administrator.switcher = 'd';
            hospitalAdministrator.switchScene("DoctorLoginScene.fxml");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    void patientSideOnAction(ActionEvent event) {
        try {
            Hospital_Administrator.switcher = 'p';
            hospitalAdministrator.switchScene("patientLoginScene.fxml");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    void receptionistSideOnAction(ActionEvent event) {
        try {
            Hospital_Administrator.switcher = 'r';
            hospitalAdministrator.switchScene("receptionistLogin.fxml");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
