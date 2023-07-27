package HMS_Controllers.ReceptionistSideControllers;

import HMS_Controllers.Hospital_Administrator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class CheckInCheckOutController {
    Hospital_Administrator hospitalAdministrator=new Hospital_Administrator();
    @FXML
    private TextField idTextField;
    @FXML
    private TextField idTextField1;


    @FXML
    void cancelOnAction(ActionEvent event) {
        try {
            Hospital_Administrator.switcher = 'r';
            hospitalAdministrator.switchScene("patientManagementScene.fxml");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void checkInOnAction(ActionEvent event) {
        String id=idTextField.getText();
        if (id.length()==0){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ID MISSING");
            alert.setHeaderText("ID IS MISSING");
            alert.setContentText("YOU HAVE TO PROVIDE THE ID OF THE PATIENT IN ORDER TO CHECK IN THE PATIENT!!");
            alert.showAndWait();
        }
        else {
            boolean found=false;
            int index=-1;
            for (int i=0;i<hospitalAdministrator.getPatientList().size();i++){
                if (id.equals(hospitalAdministrator.getPatientList().get(i).getPatientID()) ){
                    found=true;
                    index=i;
                }
            }
            if (found){
                if (hospitalAdministrator.getPatientList().get(index).getCheckInValue()!="Checked In"){
                    try {
                        Hospital_Administrator.switcher = 'r';
                        hospitalAdministrator.switchScene("patientManagementScene.fxml");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    hospitalAdministrator.getPatientList().get(index).setCheckInValue("Checked In");
                    hospitalAdministrator.getPatientList().get(index).setBill(200);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("PATIENT CHECKED IN");
                    alert.setHeaderText("PATIENT SUCCESSFULLY CHECKED IN");
                    alert.setContentText("THE PATIENT SUCCESSFULLY CHECKED IN TO THE RECORDS!!");
                    alert.showAndWait();
                }
                else {
                    try {
                        Hospital_Administrator.switcher = 'r';
                        hospitalAdministrator.switchScene("patientManagementScene.fxml");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("PATIENT ALREADY CHECKED IN");
                    alert.setHeaderText("PATIENT HAVE ALREADY CHECKED IN");
                    alert.setContentText("THE PATIENT HAVE BEEN MARKED CHECKED IN PREVIOUSLY!!");
                    alert.showAndWait();
                }
            }
            else {
                try {
                    Hospital_Administrator.switcher = 'r';
                    hospitalAdministrator.switchScene("patientManagementScene.fxml");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("PATIENT DOESN'T EXIT IN THE RECORD");
                alert.setHeaderText("PATIENT DOESN'T EXIT IN THE RECORD");
                alert.setContentText("THE PATIENT DOESN'T EXIT IN THE RECORD\n PLEASE ADD THE PATIENT FIRST TO THE RECORDS!!!!");
                alert.showAndWait();
            }
        }
    }
    @FXML
    void checkOutOnAction(ActionEvent event) {
        String id=idTextField1.getText();
        if (id.length()==0){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ID MISSING");
            alert.setHeaderText("ID IS MISSING");
            alert.setContentText("YOU HAVE TO PROVIDE THE ID OF THE PATIENT IN ORDER TO CHECK IN THE PATIENT!!");
            alert.showAndWait();
        }
        else{
            boolean found=false;
            int index=-1;
            for (int i=0;i<hospitalAdministrator.getPatientList().size();i++){
                if (id.equals(hospitalAdministrator.getPatientList().get(i).getPatientID()) ){
                    found=true;
                    index=i;
                }
            }if (found){
                if (hospitalAdministrator.getPatientList().get(index).getCheckInValue()!="Checked Out"){
                    try {
                        Hospital_Administrator.switcher = 'r';
                        hospitalAdministrator.switchScene("patientManagementScene.fxml");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    hospitalAdministrator.getPatientList().get(index).setCheckInValue("Checked Out");
                    for (int i=0;i<hospitalAdministrator.getDoctorList().size();i++){
                        for(int j=0;j<hospitalAdministrator.getDoctorList().get(i).getDoctorAppointments().size();j++) {
                            if (hospitalAdministrator.getPatientList().get(index).getPatientID().equals(hospitalAdministrator.getDoctorList().get(i).getDoctorAppointments().get(j).getPatient().getPatientID())){
                                hospitalAdministrator.getDoctorList().get(i).getDoctorAppointments().remove(j);
                            }
                        }
                    }
                    hospitalAdministrator.getPatientList().get(index).getAppointments().remove(0,hospitalAdministrator.getPatientList().get(index).getAppointments().size());
                    hospitalAdministrator.getPatientList().get(index).setBill(0);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("PATIENT CHECKED OUT");
                    alert.setHeaderText("PATIENT SUCCESSFULLY CHECKED OUT");
                    alert.setContentText("THE PATIENT SUCCESSFULLY CHECKED OUT FROM THE RECORDS!!");
                    alert.showAndWait();
                }
                else {
                    try {
                        Hospital_Administrator.switcher = 'r';
                        hospitalAdministrator.switchScene("patientManagementScene.fxml");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("PATIENT ALREADY CHECKED OUT");
                    alert.setHeaderText("PATIENT HAVE ALREADY CHECKED OUT");
                    alert.setContentText("THE PATIENT HAVE BEEN MARKED CHECKED OUT PREVIOUSLY!!");
                    alert.showAndWait();
                }
            }
            else {
                try {
                    Hospital_Administrator.switcher = 'r';
                    hospitalAdministrator.switchScene("patientManagementScene.fxml");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("PATIENT DOESN'T EXIT IN THE RECORD");
                alert.setHeaderText("PATIENT DOESN'T EXIT IN THE RECORD");
                alert.setContentText("THE PATIENT DOESN'T EXIT IN THE RECORD\n PLEASE ADD THE PATIENT FIRST TO THE RECORDS!!!!");
                alert.showAndWait();
            }

        }
    }


}
