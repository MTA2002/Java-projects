package HMS_Controllers.DoctorSideControllers;

import HMS_Controllers.Hospital_Administrator;
import HMS_Controllers.PatientSideControllers.PatientLoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;

public class ChangePasswordController {
    Hospital_Administrator hospitalAdministrator=new Hospital_Administrator();

    @FXML
    private PasswordField confirmPasswordTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    void cancelOnAction(ActionEvent event) {
        try {
            Hospital_Administrator.switcher='d';
            hospitalAdministrator.switchScene("doctorSideManagementScene.fxml");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    void confirmOnAction(ActionEvent event) {
        String password=passwordTextField.getText();
        String confirmedPassword=confirmPasswordTextField.getText();
        if (password.length()==0 || confirmedPassword.length()==0){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("DATA MISSING");
            alert.setHeaderText("SOME DATA IS MISSING");
            alert.setContentText("YOU HAVE TO PROVIDE ALL DATA'S\nMAKE SURE EVERYTHING IS NOT EMPTY!!");
            alert.showAndWait();
        }
        else if(password.length()>0 && confirmedPassword.length()>0){
            if (!password.equals(confirmedPassword)){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("PASSWORD MISMATCH");
                alert.setHeaderText("THE PASSWORD PROVIDED DO NOT MATCH");
                alert.setContentText("YOU HAVE TO PROVIDE THE SAME\nMAKE SURE YOU HAVE PROVIDED THE SAME PASSWORD!!");
                alert.showAndWait();
            }
            else {
                hospitalAdministrator.getDoctorList().get(DoctorLoginController.index).setPassword(password);
                try {
                    Hospital_Administrator.switcher='d';
                    hospitalAdministrator.switchScene("doctorSideManagementScene.fxml");
                } catch (Exception e) {
                    System.out.println(e);
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("PASSWORD SUCCESSFULLY CHANGED");
                alert.setHeaderText("YOUR PASSWORD IS SUCCESSFULLY CHANGED!!");
                alert.setContentText("THE PREVIOUS PASSWORD IS SUCCESSFULLY CHANGED TO A NEW ONE!!");
                alert.showAndWait();
            }
        }
    }

}
