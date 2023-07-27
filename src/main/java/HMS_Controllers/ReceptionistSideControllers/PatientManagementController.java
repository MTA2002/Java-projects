package HMS_Controllers.ReceptionistSideControllers;

import HMS_Controllers.Hospital_Administrator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class PatientManagementController {
    Hospital_Administrator hospitalAdministrator=new Hospital_Administrator();
    @FXML
    void addPatientOnAction() {
        try {
            Hospital_Administrator.switcher='r';
            hospitalAdministrator.switchScene("addPatientScene.fxml");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @FXML
    void checkInOnAction() {
        if (hospitalAdministrator.getPatientList().size() == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("NO PATIENT IN THE RECORD");

            alert.setContentText("No PATIENT HAS BEEN ADDED TO THE RECORD!!");
            alert.showAndWait();
        } else {
            try {
                Hospital_Administrator.switcher = 'r';
                hospitalAdministrator.switchScene("checkInPatientScene.fxml");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @FXML
    void checkOutOnAction() {
        if (hospitalAdministrator.getPatientList().size() == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("NO PATIENT IN THE RECORD");

            alert.setContentText("No PATIENT HAS BEEN ADDED TO THE RECORD!!");
            alert.showAndWait();
        } else {
            try {
                Hospital_Administrator.switcher = 'r';
                hospitalAdministrator.switchScene("checkOutPatient.fxml");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void displayPatientOnAction() {
        if (hospitalAdministrator.getPatientList().size() == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("NO PATIENT IN THE RECORD");

            alert.setContentText("No PATIENT HAS BEEN ADDED TO THE RECORD!!");
            alert.showAndWait();
        } else {
            try {
                Hospital_Administrator.switcher='r';
                hospitalAdministrator.switchScene("displayAllPatientScene.fxml");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void goBackOnAction() {
        try {
            Hospital_Administrator.switcher='r';
            hospitalAdministrator.switchScene("receptionistSideManagementScene.fxml");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    void removePatientOnAction() {
        if (hospitalAdministrator.getPatientList().size()==0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("NO PATIENT IN THE RECORD");
            alert.setContentText("No PATIENT HAS BEEN ADDED TO THE RECORD!!");
            alert.showAndWait();
        }
        else{
            try {
                Hospital_Administrator.switcher='r';
                hospitalAdministrator.switchScene("removePatientScene.fxml");
            }catch (Exception e){
                System.out.println(e);
            }

        }
    }

    @FXML
    void searchPatientOnAction() {
         if (hospitalAdministrator.getPatientList().size()==0){
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("Information");
             alert.setHeaderText("NO PATIENT IN THE RECORD");
             alert.setContentText("No PATIENT HAS BEEN ADDED TO THE RECORD!!");
             alert.showAndWait();
         }
         else{
             try {
                 Hospital_Administrator.switcher='r';
                 hospitalAdministrator.switchScene("searchPatientScene.fxml");
             }catch (Exception e){
                 System.out.println(e);
             }

         }
    }

    @FXML
    void updatePatientOnAction() {
        if (hospitalAdministrator.getPatientList().size()==0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("NO PATIENT IN THE RECORD");
            alert.setContentText("No PATIENT HAS BEEN ADDED TO THE RECORD!!");
            alert.showAndWait();
        }
        else{
            try {
                Hospital_Administrator.switcher='r';
                hospitalAdministrator.switchScene("updateScene.fxml");
            }catch (Exception e){
                System.out.println(e);
            }

        }
    }

}
