package HMS_Controllers.ReceptionistSideControllers;

import HMS_Controllers.Hospital_Administrator;
import HMS_Controllers.PatientSideControllers.PatientLoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class RemovePatientController {
    Hospital_Administrator hospitalAdministrator=new Hospital_Administrator();
    private int index;
    @FXML
    private TextField idTextField;

    @FXML
    void cancelOnAction(ActionEvent event) {
        try {
            Hospital_Administrator.switcher='r';
            hospitalAdministrator.switchScene("patientManagementScene.fxml");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @FXML
    void removeOnAction(ActionEvent event) {
        String id = idTextField.getText();
        if (id.length() == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ID MISSING");
            alert.setHeaderText("ID IS MISSING");
            alert.setContentText("PLEASE PROVIDE THE ID OF THE PATIENT YOU WANT TO UPDATE!!");
            alert.setContentText("PLEASE PROVIDE THE ID OF THE PATIENT YOU WANT TO UPDATE!!");
            alert.showAndWait();
        } else {
            boolean found = false;
            for (int i = 0; i < hospitalAdministrator.getPatientList().size(); i++) {
                if (hospitalAdministrator.getPatientList().get(i).getPatientID().equals(id)) {
                    found = true;
                    index = i;
                    break;
                }
            }
            if (found) {
                try {
                    for (int i=0;i<hospitalAdministrator.getDoctorList().size();i++){
                        for(int j=0;j<hospitalAdministrator.getDoctorList().get(i).getDoctorAppointments().size();j++) {
                            if (hospitalAdministrator.getPatientList().get(index).getPatientID().equals(hospitalAdministrator.getDoctorList().get(i).getDoctorAppointments().get(j).getPatient().getPatientID())){
                                 hospitalAdministrator.getDoctorList().get(i).getDoctorAppointments().remove(j);
                            }
                         }
                      }
                    Hospital_Administrator.switcher='r';
                    hospitalAdministrator.getPatientList().get(index).getAppointments().remove(0,hospitalAdministrator.getPatientList().get(index).getAppointments().size());
                    hospitalAdministrator.getPatientList().remove(index);
                    hospitalAdministrator.switchScene("patientManagementScene.fxml");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setHeaderText("THE RECORD IS FOUND");
                    alert.setContentText("PATIENT IS DELETED SUCCESSFULLY");
                    alert.showAndWait();

                } catch (Exception e) {
                    System.out.println(e);
                }

            } else {
                try {
                    Hospital_Administrator.switcher='r';
                    hospitalAdministrator.switchScene("patientManagementScene.fxml");
                } catch (Exception e) {
                    System.out.println(e);
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("RECORD NOT FOUND");
                alert.setContentText("THE PATIENT YOU ARE LOOKING FOR IS NOT IN THE RECORDS!!!");
                alert.showAndWait();
            }
        }
    }

}
