package HMS_Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    private int passwordTryCounter;
    private final int maxPasswordCount=4;
    private  Hospital_Administrator hospitalAdministrator =new Hospital_Administrator();
    @FXML
    private PasswordField passwordTextField=new PasswordField();

    @FXML
    private  TextField usernameTextField=new TextField();


    @FXML
    void loginButtonOnAction() {
        boolean check=false;
        String username1=usernameTextField.getText();
        String password1=passwordTextField.getText();
           for (int i = 0; i< Hospital_Administrator.admins.size() ; i++){
               if (Hospital_Administrator.admins.get(i).getName().equals(usernameTextField.getText()) && Hospital_Administrator.admins.get(i).getPassword().equals(passwordTextField.getText())){
                   check=true;
               }


           }
               if (check) {
                   try {
                       Hospital_Administrator.switcher='A';
                       hospitalAdministrator.switchScene("adminManagementScene.fxml");
                   } catch (Exception e) {
                       throw new RuntimeException(e);
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
                       alert.setContentText("YOU HAVE TRIED MAXIMUM TIMES TO LOGIN \nPLEASE TRY AGAIN LATER!!");
                       alert.showAndWait();
                   } catch (Exception e) {
                       throw new RuntimeException(e);
                   }
               }

    }
    @FXML
    void CancelButtonOnAction() {
        try {
            Hospital_Administrator.switcher='A';
            hospitalAdministrator.switchScene("HomeScene.fxml");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    @FXML
    void noAccountOnAction() {
        try {
            Hospital_Administrator.switcher='A';
            hospitalAdministrator.switchScene("createAcc.fxml");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
