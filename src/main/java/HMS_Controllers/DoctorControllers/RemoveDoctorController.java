package HMS_Controllers.DoctorControllers;

import HMS_Controllers.Hospital_Administrator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class RemoveDoctorController {
    Hospital_Administrator hospitalAdministrator=new Hospital_Administrator();

    @FXML
    private TextField idTextField;
    private int index;

    @FXML
    void cancelOnAction(ActionEvent event) {
        try {
            Hospital_Administrator.switcher='D';
            hospitalAdministrator.switchScene("doctorManagementScene.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void removeOnAction(ActionEvent event) {
        String id = idTextField.getText();
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
                    for (int i=0;i<hospitalAdministrator.getPatientList().size();i++){
                        for(int j=0;j<hospitalAdministrator.getPatientList().get(i).getAppointments().size();j++) {
                            if (hospitalAdministrator.getDoctorList().get(index).getStaffId().equals(hospitalAdministrator.getPatientList().get(i).getAppointments().get(j).getDoctor().getStaffId())){
                                hospitalAdministrator.getPatientList().get(i).getAppointments().remove(j);
                            }
                        }
                    }
                    Hospital_Administrator.switcher='D';
                    hospitalAdministrator.getDoctorList().get(index).getDoctorAppointments().remove(0, hospitalAdministrator.getDoctorList().get(index).getDoctorAppointments().size());
                    hospitalAdministrator.getDoctorList().remove(index);
                    hospitalAdministrator.switchScene("doctorManagementScene.fxml");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setHeaderText("THE RECORD IS FOUND");
                    alert.setContentText("DOCTOR IS DELETED SUCCESSFULLY");
                    alert.showAndWait();

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
}