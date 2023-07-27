package HMS_Controllers.DoctorControllers;

import HMS_Classes.Doctor;
import HMS_Controllers.Hospital_Administrator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.function.UnaryOperator;

public class UpdateDoctorController {
    Hospital_Administrator hospitalAdministrator = new Hospital_Administrator();
    protected static int index;


    protected static String id1;
    @FXML
    TextField idTextField = new TextField();


    @FXML
    void updateOnAction(ActionEvent event) {
        String id = idTextField.getText();
        id1=id;
        if (id.length() == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ID MISSING");
            alert.setHeaderText("ID IS MISSING");
            alert.setContentText("PLEASE PROVIDE THE ID OF THE DOCTOR YOU WANT TO UPDATE!!");
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
                    Hospital_Administrator.switcher = 'D';
                    hospitalAdministrator.switchScene("updateDoctorScene.fxml");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            } else {
                try {
                    Hospital_Administrator.switcher = 'D';
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
    void goBackOnAction(ActionEvent event) {
        try {
            Hospital_Administrator.switcher = 'D';
            hospitalAdministrator.switchScene("doctorManagementScene.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





}