package HMS_Controllers.DoctorControllers;

import HMS_Controllers.Hospital_Administrator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class SearchDoctorController {
    private int index;
    Hospital_Administrator hospitalAdministrator=new Hospital_Administrator();
    @FXML
    private TextField idTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    void goBackOnAction(ActionEvent event) {
        try {
            Hospital_Administrator.switcher='D';
            hospitalAdministrator.switchScene("doctorManagementScene.fxml");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void searchById() {
        try {
            Hospital_Administrator.switcher='D';
            hospitalAdministrator.switchScene("searchByIdScene.fxml");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void searchByName() {
        try {
            Hospital_Administrator.switcher='D';
            hospitalAdministrator.switchScene("searchByNameScene.fxml");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    void searchByNameOnAction() {
        String name = nameTextField.getText();
        if (name.length()==0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("NAME MISSING");
            alert.setHeaderText("NAME IS MISSING");
            alert.setContentText("PLEASE PROVIDE THE NAME OF THE DOCTOR YOU WANT TO SEARCH FOR!!");
            alert.showAndWait();
        } else {
            boolean found = false;
            for (int i = 0; i < hospitalAdministrator.getDoctorList().size(); i++) {
                if (hospitalAdministrator.getDoctorList().get(i).getName().equals(name)) {
                    found = true;
                    index = i;
                    break;
                }
            }
            if (found) {
                try {
                    hospitalAdministrator.getFoundDoctorList().remove(0,hospitalAdministrator.getFoundDoctorList().size());
                    hospitalAdministrator.getFoundDoctorList().add(hospitalAdministrator.getDoctorList().get(index));
                    Hospital_Administrator.switcher='D';
                    hospitalAdministrator.switchScene("found.fxml");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            } else {
                try {
                    Hospital_Administrator.switcher='D';
                    hospitalAdministrator.switchScene("doctorManagementScene.fxml");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("RECORD NOT FOUND");
                alert.setContentText("THE DOCTOR YOU ARE LOOKING FOR IS NOT IN THE RECORDS!!!");
                alert.showAndWait();
            }


        }
    }
    @FXML
    void searchByIdOnAction(ActionEvent event) {
        String id=idTextField.getText();
        if (id.length()==0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ID MISSING");
            alert.setHeaderText("ID IS MISSING");
            alert.setContentText("PLEASE PROVIDE THE ID OF THE DOCTOR YOU WANT TO SEARCH FOR!!");
            alert.showAndWait();
        } else {
            boolean found = false;
            for (int i = 0; i < hospitalAdministrator.getDoctorList().size(); i++) {
                if (hospitalAdministrator.getDoctorList().get(i).getStaffId().equals(id)) {
                    found = true;
                    index = i;
                    break;
                }
            }
            if (found) {
                try {
                    hospitalAdministrator.getFoundDoctorList().remove(0,hospitalAdministrator.getFoundDoctorList().size());
                    hospitalAdministrator.getFoundDoctorList().add(hospitalAdministrator.getDoctorList().get(index));
                    Hospital_Administrator.switcher='D';
                    hospitalAdministrator.switchScene("found.fxml");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            } else {
                try {
                    Hospital_Administrator.switcher='D';
                    hospitalAdministrator.switchScene("doctorManagementScene.fxml");
                } catch (Exception w) {
                    throw new RuntimeException(w);
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("RECORD NOT FOUND");
                alert.setContentText("THE DOCTOR YOU ARE LOOKING FOR IS NOT IN THE RECORDS!!!");
                alert.showAndWait();
            }


        }
    }


}