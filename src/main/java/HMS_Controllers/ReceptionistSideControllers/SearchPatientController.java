package HMS_Controllers.ReceptionistSideControllers;

import HMS_Controllers.Hospital_Administrator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class SearchPatientController {
    Hospital_Administrator hospitalAdministrator=new Hospital_Administrator();

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField idTextField;


    @FXML
    void goBackOnAction(ActionEvent event) {
         try {
             Hospital_Administrator.switcher='r';
             hospitalAdministrator.switchScene("patientManagementScene.fxml");
         }catch (Exception e){
             System.out.println(e);
         }
    }

    @FXML
    void searchById(ActionEvent event) {
            Hospital_Administrator.switcher='r';
            try {
                hospitalAdministrator.switchScene("searchByIdScene.fxml");
            }catch (Exception e){
                System.out.println(e);
            }
    }

    @FXML
    void searchByName(ActionEvent event) {
        Hospital_Administrator.switcher='r';
        try {
            hospitalAdministrator.switchScene("searchByNameScene.fxml");
        }catch (Exception e){
            System.out.println(e);
        }
    }
    @FXML
    void searchByNameOnAction(ActionEvent event) {
        String name=nameTextField.getText();
        if (name.length()==0){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("NAME MISSING");
            alert.setHeaderText("NAME IS MISSING");
            alert.setContentText("PLEASE PROVIDE THE ID OF THE RECEPTIONIST YOU WANT TO SEARCH FOR!!");
            alert.showAndWait();
        }else {
            int index=-1;
            boolean found=false;
            for (int i=0;i<hospitalAdministrator.getPatientList().size();i++){
                if (name.equals(hospitalAdministrator.getPatientList().get(i).getName())){
                    index=i;
                    found=true;
                }
            }
            if (found){
                hospitalAdministrator.getFoundPatientList().remove(0,hospitalAdministrator.getFoundPatientList().size());
                hospitalAdministrator.getFoundPatientList().add(hospitalAdministrator.getPatientList().get(index));
                Hospital_Administrator.switcher='r';
                try {
                    hospitalAdministrator.switchScene("found.fxml");
                }catch (Exception e){
                    System.out.println(e);
                }

            }
            else {
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
    @FXML
    void searchByIdOnAction(ActionEvent event) {
        String id=idTextField.getText();
        if (id.length()==0){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ID MISSING");
            alert.setHeaderText("ID IS MISSING");
            alert.setContentText("PLEASE PROVIDE THE ID OF THE RECEPTIONIST YOU WANT TO SEARCH FOR!!");
            alert.showAndWait();
        }else {
            int index=-1;
            boolean found=false;
            for (int i=0;i<hospitalAdministrator.getPatientList().size();i++){
                if (id.equals(hospitalAdministrator.getPatientList().get(i).getPatientID())){
                    index=i;
                    found=true;
                }
            }
            if (found){
              hospitalAdministrator.getFoundPatientList().remove(0,hospitalAdministrator.getFoundPatientList().size());
              hospitalAdministrator.getFoundPatientList().add(hospitalAdministrator.getPatientList().get(index));
              Hospital_Administrator.switcher='r';
              try {
                  hospitalAdministrator.switchScene("found.fxml");
              }catch (Exception e){
                  System.out.println(e);
              }

            }
            else {
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
