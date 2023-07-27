package HMS_Controllers.DoctorControllers;

import HMS_Classes.Doctor;
import HMS_Controllers.Hospital_Administrator;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.function.UnaryOperator;

public class AddDoctorController {
    Hospital_Administrator hospitalAdministrator = new Hospital_Administrator();
    @FXML
    private ChoiceBox<Integer> ageChoiceBox;

    @FXML
    private TextField nameTextField;
    @FXML
    private RadioButton femaleRadioButton;
    private String gender;
    @FXML
    private RadioButton maleRadioButton;
    @FXML
    private TextField phoneTextField;
    @FXML
    private TextField salaryTextField;


    @FXML
    private ChoiceBox<String> specialityChoiceBox;

    @FXML
    void cancelOnAction() {
        try {
            Hospital_Administrator.switcher = 'D';
            hospitalAdministrator.switchScene("doctorManagementScene.fxml");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void saveOnAction() {
        String name = nameTextField.getText();
        Integer age = ageChoiceBox.getValue();
        String phone = phoneTextField.getText();
        String salary = salaryTextField.getText();
        String speciality = specialityChoiceBox.getValue();
        if (name.length() == 0 || age == null || phone.length() == 0 || salary.length() == 0 || speciality == null || gender == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("DATA MISSING");
            alert.setHeaderText("SOME DATA IS MISSING");
            alert.setContentText("YOU HAVE TO PROVIDE ALL DATA'S\nMAKE SURE EVERYTHING IS NOT EMPTY!!");
            alert.showAndWait();
        } else {
            String doctorId = "";
            Hospital_Administrator.doctorIdCount++;
            if (Hospital_Administrator.doctorIdCount < 10) {
                doctorId += "D000" + Hospital_Administrator.doctorIdCount + "/15";
            } else if (Hospital_Administrator.doctorIdCount < 100 && Hospital_Administrator.doctorIdCount >= 10) {
                doctorId += "D00" + Hospital_Administrator.doctorIdCount + "/15";
            } else if (Hospital_Administrator.doctorIdCount < 1000 && Hospital_Administrator.doctorIdCount >= 100) {
                doctorId += "D0" + Hospital_Administrator.doctorIdCount + "/15";
            }
            Doctor doctor = new Doctor(name, age, phone, gender, Hospital_Administrator.defaultPassword, doctorId, Double.parseDouble(salary), speciality);
            hospitalAdministrator.getDoctorList().add(doctor);
            try {
                Hospital_Administrator.switcher = 'D';
                hospitalAdministrator.switchScene("doctorManagementScene.fxml");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("DOCTOR ADDED SUCCESSFULLY");
                alert.setHeaderText("DOCTOR HAS BEEN ADDED SUCCESSFULLY");
                alert.setContentText("THE DOCTOR HAS BEEN ADDED SUCCESSFULLY TO THE LIST WITH THE ID OF " + doctorId);
                alert.showAndWait();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void initialize() {

        for (int i = 21; i <= 80; i++) {
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
        specialityChoiceBox.getItems().add("General Practitioner");
        specialityChoiceBox.getItems().add("Surgery");
        specialityChoiceBox.getItems().add("Pediatrics");
        specialityChoiceBox.getItems().add("Cardiology");
        specialityChoiceBox.getItems().add("Dentistry");
        UnaryOperator<TextFormatter.Change> salaryFilter = change -> {
            String text = change.getText();
            if (text.matches("[0-9]*")) {
                return change;
            }
            return null;
        };
        TextFormatter<String> salaryFormatter = new TextFormatter<>(salaryFilter);
        salaryTextField.setTextFormatter(salaryFormatter);

    }
}

