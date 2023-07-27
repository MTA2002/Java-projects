package HMS_Controllers.DoctorSideControllers;

import HMS_Controllers.Hospital_Administrator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class DoctorLoginController {
    Hospital_Administrator hospitalAdministrator=new Hospital_Administrator();

    protected static int index;
    private int passwordTryCounter;
    private final int maxPasswordCount=4;
    @FXML
    private PasswordField passwordTextField;

    @FXML
    private TextField usernameTextField;

    @FXML
    void CancelButtonOnAction(ActionEvent event) {
        try {
            Hospital_Administrator.switcher='A';
            hospitalAdministrator.switchScene("HomeScene.fxml");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    void loginButtonOnAction(ActionEvent event) {
        boolean check=false;
        String username1=usernameTextField.getText();
        String password1=passwordTextField.getText();
        for (int i = 0; i< hospitalAdministrator.getDoctorList().size() ; i++){
            if (hospitalAdministrator.getDoctorList().get(i).getName().equals(usernameTextField.getText()) && hospitalAdministrator.getDoctorList().get(i).getPassword().equals(passwordTextField.getText())){
                check=true;
                index=i;
            }
        }
        if (check) {
            try {
                Hospital_Administrator.switcher='d';
                hospitalAdministrator.switchScene("doctorSideManagementScene.fxml");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        else  {
            if(usernameTextField.getText().length()!=0 && passwordTextField.getText().length()!=0){
                passwordTryCounter++;
            }
        }
        if (passwordTryCounter>=maxPasswordCount){
            try {
                Hospital_Administrator.switcher='A';
                hospitalAdministrator.switchScene("HomeScene.fxml");
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("WRONG PASSWORD");
                alert.setHeaderText("WRONG PASSWORD PROVIDED");
                alert.setContentText("YOU HAVE TRIED MAXIMUM TIMES TO LOGIN \nPLEASE TRY AGAIN LATER OR CONTACT ADMIN FOR AN ACCOUNT!!");
                alert.showAndWait();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

}
