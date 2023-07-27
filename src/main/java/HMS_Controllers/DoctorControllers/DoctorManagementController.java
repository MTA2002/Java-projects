package HMS_Controllers.DoctorControllers;

import HMS_Controllers.Hospital_Administrator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class DoctorManagementController {
    Hospital_Administrator hospital_administrator = new Hospital_Administrator();

    @FXML
    void addDoctorOnAction(ActionEvent event) {
        try {
            Hospital_Administrator.switcher='D';
            hospital_administrator.switchScene("addDoctorScene.fxml");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void displayDoctorOnAction(ActionEvent event) {
        if (hospital_administrator.getDoctorList().size() == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("NO DOCTOR IN THE RECORD");

            alert.setContentText("No DOCTOR HAS BEEN ADDED TO THE RECORD!!");
            alert.showAndWait();
        } else {
            try {
                Hospital_Administrator.switcher='D';
                hospital_administrator.switchScene("displayAllDoctorScene.fxml");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void goBackOnAction(ActionEvent event) {
        try {
            Hospital_Administrator.switcher='A';
            hospital_administrator.switchScene("adminManagementScene.fxml");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void removeDoctorOnAction(ActionEvent event) {
        if (hospital_administrator.getDoctorList().size() == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("NO DOCTOR IN THE RECORD");

            alert.setContentText("No DOCTOR HAS BEEN ADDED TO THE RECORD!!");
            alert.showAndWait();
        } else {
            try {
                Hospital_Administrator.switcher='D';
                hospital_administrator.switchScene("removeDoctorScene.fxml");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void searchDoctorOnAction(ActionEvent event) {
        if (hospital_administrator.getDoctorList().size() == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("NO DOCTOR IN THE RECORD");

            alert.setContentText("No DOCTOR HAS BEEN ADDED TO THE RECORD!!");
            alert.showAndWait();
        } else {
            try {
                Hospital_Administrator.switcher='D';
                hospital_administrator.switchScene("searchDoctorScene.fxml");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void updateDoctorOnAction(ActionEvent event) {
        if (hospital_administrator.getDoctorList().size() == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("NO DOCTOR IN THE RECORD");

            alert.setContentText("No DOCTOR HAS BEEN ADDED TO THE RECORD!!");
            alert.showAndWait();
        } else {
            try {
                Hospital_Administrator.switcher='D';
                hospital_administrator.switchScene("updateScene.fxml");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
