package HMS_Controllers.ReceptionistControllers;

import HMS_Controllers.Hospital_Administrator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class SearchReceptionistController {
    private int index;
    Hospital_Administrator hospitalAdministrator=new Hospital_Administrator();
    @FXML
    private TextField idTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    void goBackOnAction(ActionEvent event) {
        try {
            Hospital_Administrator.switcher='R';
            hospitalAdministrator.switchScene("ReceptionistManagementScene.fxml");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    void searchById() {
        try {
            Hospital_Administrator.switcher='R';
            hospitalAdministrator.switchScene("searchByIdScene.fxml");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    void searchByName() {
        try {
            Hospital_Administrator.switcher='R';
            hospitalAdministrator.switchScene("searchByNameScene.fxml");
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    @FXML
    void searchByNameOnAction() {
        String name = nameTextField.getText();
        if (name.length()==0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("NAME MISSING");
            alert.setHeaderText("NAME IS MISSING");
            alert.setContentText("PLEASE PROVIDE THE NAME OF THE RECEPTIONIST YOU WANT TO SEARCH FOR!!");
            alert.showAndWait();
        } else {
            boolean found = false;
            for (int i = 0; i < hospitalAdministrator.getReceptionistList().size(); i++) {
                if (hospitalAdministrator.getReceptionistList().get(i).getName().equals(name)) {
                    found = true;
                    index = i;
                    break;
                }
            }
            if (found) {
                try {
                    hospitalAdministrator.getReceptionistList().remove(0,hospitalAdministrator.getFoundReceptionistList().size());
                    hospitalAdministrator.getFoundReceptionistList().add(hospitalAdministrator.getReceptionistList().get(index));
                    Hospital_Administrator.switcher='R';
                    hospitalAdministrator.switchScene("found.fxml");
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
    @FXML
    void searchByIdOnAction(ActionEvent event) {
        String id=idTextField.getText();
        if (id.length()==0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ID MISSING");
            alert.setHeaderText("ID IS MISSING");
            alert.setContentText("PLEASE PROVIDE THE ID OF THE RECEPTIONIST YOU WANT TO SEARCH FOR!!");
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
                    hospitalAdministrator.getFoundReceptionistList().remove(0,hospitalAdministrator.getFoundReceptionistList().size());
                    hospitalAdministrator.getFoundReceptionistList().add(hospitalAdministrator.getReceptionistList().get(index));
                    Hospital_Administrator.switcher='R';
                    hospitalAdministrator.switchScene("found.fxml");
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