package HMS_Controllers.PatientSideControllers;

import HMS_Controllers.Hospital_Administrator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class AppointmentManagementController {
     Hospital_Administrator hospitalAdministrator=new Hospital_Administrator();
    @FXML
    void cancelAnAppointmentOnAction(ActionEvent event) {if (hospitalAdministrator.getPatientList().get(PatientLoginController.index).getAppointments().size() == 0) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("NO APPOINTMENT IN THE RECORD");
        alert.setContentText("No APPOINTMENT HAS BEEN ADDED TO THE RECORD!!");
        alert.showAndWait();
    } else {
        try {
            Hospital_Administrator.switcher = 'p';
            hospitalAdministrator.switchScene("cancelAppointmentScene.fxml");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    }

    @FXML
    void goBackOnAction(ActionEvent event) {
        try {
            Hospital_Administrator.switcher='p';
            hospitalAdministrator.switchScene("patientSideManagementScene.fxml");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    void rescheduleAnAppointmentOnAction(ActionEvent event) {
        if (hospitalAdministrator.getPatientList().get(PatientLoginController.index).getAppointments().size() == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("NO APPOINTMENT IN THE RECORD");
            alert.setContentText("No APPOINTMENT HAS BEEN ADDED TO THE RECORD!!");
            alert.showAndWait();
        } else {
            try {
                Hospital_Administrator.switcher = 'p';
                hospitalAdministrator.switchScene("rescheduleAppointmentScene1.fxml");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    @FXML
    void scheduleAnAppointmentOnAction(ActionEvent event) {
        try {
            Hospital_Administrator.switcher='p';
            hospitalAdministrator.switchScene("scheduleAppointmentScene.fxml");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    void viewAllAppointmentsOnAction(ActionEvent event) {
        if (hospitalAdministrator.getPatientList().get(PatientLoginController.index).getAppointments().size() == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("NO APPOINTMENT IN THE RECORD");
            alert.setContentText("No APPOINTMENT HAS BEEN ADDED TO THE RECORD!!");
            alert.showAndWait();
        } else {
            try {
                Hospital_Administrator.switcher = 'p';
                hospitalAdministrator.switchScene("viewAppointmentScene.fxml");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

}
