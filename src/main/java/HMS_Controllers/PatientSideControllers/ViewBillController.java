package HMS_Controllers.PatientSideControllers;

import HMS_Controllers.Hospital_Administrator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ViewBillController {
    Hospital_Administrator hospitalAdministrator=new Hospital_Administrator();
    @FXML
    private Label billLabel;

    @FXML
    public void initialize(){
        billLabel.setText("Your Bill at the moment is: "+hospitalAdministrator.getPatientList().get(PatientLoginController.index).getBill()+" Birr\n Thanks for using our services");
    }
    @FXML
    void goBackOnAction() {
        try {
            Hospital_Administrator.switcher='p';
            hospitalAdministrator.switchScene("patientSideManagementScene.fxml");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
