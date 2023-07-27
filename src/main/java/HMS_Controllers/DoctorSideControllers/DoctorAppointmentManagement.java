package HMS_Controllers.DoctorSideControllers;

import HMS_Controllers.Hospital_Administrator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class DoctorAppointmentManagement {
    Hospital_Administrator hospitalAdministrator=new Hospital_Administrator();
    @FXML
    void cancelAnAppointmentOnAction(ActionEvent event) {
        try {
            Hospital_Administrator.switcher = 'd';
            hospitalAdministrator.switchScene("cancelAppointmentScene.fxml");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    void goBackOnAction(ActionEvent event) {
        try {
            Hospital_Administrator.switcher='d';
            hospitalAdministrator.switchScene("doctorSideManagementScene.fxml");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    void rescheduleAnAppointmentOnAction(ActionEvent event) {
        try {
            Hospital_Administrator.switcher = 'd';
            hospitalAdministrator.switchScene("rescheduleAppointmentScene1.fxml");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    void viewAllAppointmentsOnAction(ActionEvent event) {
        try {
            Hospital_Administrator.switcher = 'd';
            hospitalAdministrator.switchScene("viewAppointmentScene.fxml");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
