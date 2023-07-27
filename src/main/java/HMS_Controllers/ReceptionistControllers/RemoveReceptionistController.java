package HMS_Controllers.ReceptionistControllers;

import HMS_Controllers.Hospital_Administrator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class RemoveReceptionistController {
    Hospital_Administrator hospitalAdministrator=new Hospital_Administrator();

    @FXML
    private TextField idTextField;
    private int index;

    @FXML
    void cancelOnAction(ActionEvent event) {
        try {
            Hospital_Administrator.switcher='R';
            hospitalAdministrator.switchScene("ReceptionistManagementScene.fxml");
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
            alert.setContentText("PLEASE PROVIDE THE ID OF THE RECEPTIONIST YOU WANT TO REMOVE!!");
            alert.setContentText("PLEASE PROVIDE THE ID OF THE RECEPTIONIST YOU WANT TO REMOVE!!");
            alert.showAndWait();
        } else {
            boolean found = false;
            for (int i = 0; i < hospitalAdministrator.getReceptionistList().size(); i++) {
                if (hospitalAdministrator.getReceptionistList().get(i).getStaffId().equals(id)) {
                    found = true;
                    index = i;
                    break;
                }
            }
            if (found) {
                try {
                    Hospital_Administrator.switcher='R';
                    hospitalAdministrator.getReceptionistList().remove(index);
                    hospitalAdministrator.switchScene("ReceptionistManagementScene.fxml");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setHeaderText("THE RECORD IS FOUND");
                    alert.setContentText("RECEPTIONIST IS DELETED SUCCESSFULLY");
                    alert.showAndWait();

                } catch (Exception e) {
                    System.out.println(e);
                }

            } else {
                try {
                    Hospital_Administrator.switcher='R';
                    hospitalAdministrator.switchScene("ReceptionistManagementScene.fxml");
                } catch (Exception e) {
                    System.out.println(e);
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("RECORD NOT FOUND");
                alert.setContentText("THE RECEPTIONIST YOU ARE LOOKING FOR IS NOT IN THE RECORDS!!!");
                alert.showAndWait();
            }
        }
    }
}