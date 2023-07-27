package HMS_Controllers.ReceptionistSideControllers;

import HMS_Classes.Patient;
import HMS_Classes.Receptionist;
import HMS_Controllers.Hospital_Administrator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.function.UnaryOperator;

public class AddPatientController {
    Hospital_Administrator hospitalAdministrator = new Hospital_Administrator();
    @FXML
    private ChoiceBox<Integer> ageChoiceBox;

    @FXML
    private RadioButton femaleRadioButton;
    private String gender;
    @FXML
    private RadioButton maleRadioButton;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField phoneTextField;

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
    void saveOnAction(ActionEvent event) {
        String name = nameTextField.getText();
        Integer age = ageChoiceBox.getValue();
        String phone = phoneTextField.getText();

        if (name.length() == 0 || age == null || phone.length() == 0 || gender == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("DATA MISSING");
            alert.setHeaderText("SOME DATA IS MISSING");
            alert.setContentText("YOU HAVE TO PROVIDE ALL DATA'S\nMAKE SURE EVERYTHING IS NOT EMPTY!!");
            alert.showAndWait();
        } else {
            String patientId = "";
            Hospital_Administrator.patientIdCount++;
            if (Hospital_Administrator.patientIdCount < 10) {
                patientId += "P000" + Hospital_Administrator.patientIdCount + "/15";
            } else if (Hospital_Administrator.patientIdCount < 100 && Hospital_Administrator.patientIdCount >= 10) {
                patientId += "P00" + Hospital_Administrator.patientIdCount + "/15";
            } else if (Hospital_Administrator.patientIdCount < 1000 && Hospital_Administrator.receptionistIdCount >= 100) {
                patientId += "P0" + Hospital_Administrator.patientIdCount + "/15";
            }
            Patient patient = new Patient(name, age, phone, gender, Hospital_Administrator.defaultPassword, patientId, true);
            patient.setCheckInValue("Checked In");
            patient.setBill(200);
            hospitalAdministrator.getPatientList().add(patient);
            try {
                Hospital_Administrator.switcher = 'r';
                hospitalAdministrator.switchScene("patientManagementScene.fxml");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("PATIENT ADDED SUCCESSFULLY");
                alert.setHeaderText("PATIENT HAS BEEN ADDED SUCCESSFULLY");
                alert.setContentText("THE PATIENT HAS BEEN ADDED SUCCESSFULLY TO THE LIST WITH THE ID OF " + patientId);
                alert.showAndWait();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void initialize() {

        for (int i = 1; i <= 100; i++) {
            ageChoiceBox.getItems().add(i);
        }
        TextFormatter<String> textFormatter = new TextFormatter<>(change -> {
            if (!change.getText().matches("[a-z A-Z]+")) {
                change.setText("");
            }
            return change;
        });
        nameTextField.setTextFormatter(textFormatter);
        UnaryOperator<TextFormatter.Change> phoneFilter = change -> {
            String text = change.getText();
            if (text.matches("[+0-9]*") && change.getControlText().length() + text.length() <= 13) {
                return change;
            }
            return null;
        };
        ToggleGroup toggleGroup = new ToggleGroup();
        maleRadioButton.setToggleGroup(toggleGroup);
        femaleRadioButton.setToggleGroup(toggleGroup);
        maleRadioButton.setOnAction(event -> gender = "Male");
        femaleRadioButton.setOnAction(event -> gender = "Female");
        TextFormatter<String> phoneFormatter = new TextFormatter<>(phoneFilter);
        phoneTextField.setTextFormatter(phoneFormatter);


    }

}
