package HMS_Controllers.ReceptionistSideControllers;

import HMS_Controllers.Hospital_Administrator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ReceptionistSideManagement {
    Hospital_Administrator hospitalAdministrator=new Hospital_Administrator();
    @FXML
    void changePasswordOnAction(ActionEvent event) {
        try {
            Hospital_Administrator.switcher='r';
            hospitalAdministrator.switchScene("changePasswordScene.fxml");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    void goBackOnAction(ActionEvent event) {
        try {
            Hospital_Administrator.switcher='A';
            hospitalAdministrator.switchScene("HomeScene.fxml");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    void managePatientsOnAction(ActionEvent event) {
        try {
            Hospital_Administrator.switcher='r';
            hospitalAdministrator.switchScene("patientManagementScene.fxml");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
