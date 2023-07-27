package HMS_Controllers.DoctorSideControllers;

import HMS_Controllers.Hospital_Administrator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class DoctorSideManagement {
    Hospital_Administrator hospitalAdministrator=new Hospital_Administrator();
    @FXML
    void changePasswordOnAction(ActionEvent event) {
        try {
            Hospital_Administrator.switcher='d';
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
    void manageAppointmentsOnAction(ActionEvent event) {
        if (hospitalAdministrator.getDoctorList().get(DoctorLoginController.index).getDoctorAppointments().size()==0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("NO APPOINTMENTS AT THE MOMENT");
            alert.setContentText("COME BACK AFTER APPOINTMENTS HAVE BEEN SCHEDULED!!");
            alert.showAndWait();
        }
        else {
            try {
                Hospital_Administrator.switcher='d';
                hospitalAdministrator.switchScene("dAppointmentsManagementScene.fxml");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    @FXML
    void providePrescriptionOnAction(ActionEvent event) {
        try {
            Hospital_Administrator.switcher='d';
            hospitalAdministrator.switchScene("providePrescriptionScene1.fxml");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
