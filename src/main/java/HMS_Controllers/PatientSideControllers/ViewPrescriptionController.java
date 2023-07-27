package HMS_Controllers.PatientSideControllers;

import HMS_Classes.Prescription;
import HMS_Controllers.Hospital_Administrator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ViewPrescriptionController {
    Hospital_Administrator hospitalAdministrator=new Hospital_Administrator();
    protected static int prescriptionIndex;
    @FXML
    private TextField idTextField;


    @FXML
    void cancelOnAction(ActionEvent event) {
        try {
            Hospital_Administrator.switcher='p';
            hospitalAdministrator.switchScene("patientSideManagementScene.fxml");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    void viewOnAction(ActionEvent event) {
        String prescriptionId=idTextField.getText();
        if (prescriptionId.length()==0){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ID MISSING");
            alert.setHeaderText("ID IS MISSING");
            alert.setContentText("PLEASE PROVIDE THE ID OF THE PRESCRIPTION YOU WANT TO VIEW!!");
            alert.showAndWait();
        }else{
            boolean found = false;
            for (int i=0;i<hospitalAdministrator.getPatientList().get(PatientLoginController.index).getPrescriptions().size();i++){
                if(prescriptionId.equals(hospitalAdministrator.getPatientList().get(PatientLoginController.index).getPrescriptions().get(i).getPrescriptionID())){
                    prescriptionIndex=i;
                    found =true;
                    break;
                }
            }
            if (found){
                try {
                    Hospital_Administrator.switcher = 'p';
                    hospitalAdministrator.switchScene("viewPrescriptionScene2.fxml");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }else{
                try {
                    Hospital_Administrator.switcher='p';
                    hospitalAdministrator.switchScene("patientSideManagementScene.fxml");
                } catch (Exception e) {
                    System.out.println(e);
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("PRESCRIPTION NOT FOUND");
                alert.setContentText("THE PRESCRIPTION IS NOT FOUND IN THE RECORDS!!!");
                alert.showAndWait();
            }
        }
    }


}
