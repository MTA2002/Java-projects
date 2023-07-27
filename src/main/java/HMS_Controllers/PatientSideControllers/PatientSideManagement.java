package HMS_Controllers.PatientSideControllers;

import HMS_Controllers.Hospital_Administrator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class PatientSideManagement {
    Hospital_Administrator hospitalAdministrator=new Hospital_Administrator();
    @FXML
    void changePasswordOnAction(ActionEvent event) {
        try {
            Hospital_Administrator.switcher='p';
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
        if (hospitalAdministrator.getDoctorList().size()==0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("NO DOCTOR IN THE RECORD");
            alert.setContentText("COME BACK AFTER DOCTORS HAVE BEEN ADDED!!");
            alert.showAndWait();
        } else if (hospitalAdministrator.getPatientList().get(PatientLoginController.index).getCheckInValue().equals("Checked Out")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("FIRST YOU HAVE TO CHECK IN");
            alert.setContentText("COME BACK AFTER YOU HAVE CHECKED IN!!");
            alert.showAndWait();
        } else {
            try {
                Hospital_Administrator.switcher='p';
                hospitalAdministrator.switchScene("pAppointmentsManagementScene.fxml");
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }

    @FXML
    void viewBillOnAction(ActionEvent event) {
        try {
            Hospital_Administrator.switcher='p';
            hospitalAdministrator.switchScene("viewBillScene.fxml");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    void viewPrescriptionOnAction(ActionEvent event) {
        try {
            Hospital_Administrator.switcher='p';
            hospitalAdministrator.switchScene("viewPrescriptionScene1.fxml");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
