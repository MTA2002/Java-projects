package HMS_Controllers.ReceptionistControllers;

import HMS_Controllers.Hospital_Administrator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class ReceptionistManagementController {
    Hospital_Administrator hospital_administrator = new Hospital_Administrator();

    @FXML
    void addReceptionistOnAction(ActionEvent event) {
        try {
            Hospital_Administrator.switcher='R';
            hospital_administrator.switchScene("addReceptionistScene.fxml");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void displayReceptionistOnAction(ActionEvent event) {
        if (hospital_administrator.getReceptionistList().size() == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("NO RECEPTIONIST IN THE RECORD");

            alert.setContentText("No RECEPTIONIST HAS BEEN ADDED TO THE RECORD!!");
            alert.showAndWait();
        } else {
            try {
                Hospital_Administrator.switcher='R';
                hospital_administrator.switchScene("displayAllReceptionistScene.fxml");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void goBackOnAction(ActionEvent event) {
        try {
            Hospital_Administrator.switcher='A';
            hospital_administrator.switchScene("adminManagementScene.fxml");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void removeReceptionistOnAction(ActionEvent event) {
        if (hospital_administrator.getReceptionistList().size() == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("NO RECEPTIONIST IN THE RECORD");

            alert.setContentText("No RECEPTIONIST HAS BEEN ADDED TO THE RECORD!!");
            alert.showAndWait();
        } else {
            try {
                Hospital_Administrator.switcher='R';
                hospital_administrator.switchScene("removeReceptionistScene.fxml");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void searchReceptionistOnAction(ActionEvent event) {
        if (hospital_administrator.getReceptionistList().size() == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("NO RECEPTIONIST IN THE RECORD");

            alert.setContentText("No RECEPTIONIST HAS BEEN ADDED TO THE RECORD!!");
            alert.showAndWait();
        } else {
            try {
                Hospital_Administrator.switcher='R';
                hospital_administrator.switchScene("searchReceptionistScene.fxml");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void updateReceptionistOnAction(ActionEvent event) {
        if (hospital_administrator.getReceptionistList().size() == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("NO RECEPTIONIST IN THE RECORD");

            alert.setContentText("No RECEPTIONIST HAS BEEN ADDED TO THE RECORD!!");
            alert.showAndWait();
        } else {
            try {
                Hospital_Administrator.switcher='R';
                hospital_administrator.switchScene("updateScene.fxml");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}

