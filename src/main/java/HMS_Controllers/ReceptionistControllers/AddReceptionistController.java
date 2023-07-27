package HMS_Controllers.ReceptionistControllers;

import HMS_Classes.Receptionist;
import HMS_Controllers.Hospital_Administrator;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.function.UnaryOperator;

public class AddReceptionistController {
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
    void cancelOnAction() {
        try {
            Hospital_Administrator.switcher = 'R';
            hospitalAdministrator.switchScene("ReceptionistManagementScene.fxml");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void saveOnAction() {
        String name = nameTextField.getText();
        Integer age = ageChoiceBox.getValue();
        String phone = phoneTextField.getText();
        String salary = salaryTextField.getText();
        if (name.length() == 0 || age == null || phone.length() == 0 || salary.length() == 0 || gender == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("DATA MISSING");
            alert.setHeaderText("SOME DATA IS MISSING");
            alert.setContentText("YOU HAVE TO PROVIDE ALL DATA'S\nMAKE SURE EVERYTHING IS NOT EMPTY!!");
            alert.showAndWait();
        } else {
            String receptionistId = "";
            Hospital_Administrator.receptionistIdCount++;
            if (Hospital_Administrator.receptionistIdCount < 10) {
                receptionistId += "R000" + Hospital_Administrator.receptionistIdCount + "/15";
            } else if (Hospital_Administrator.receptionistIdCount < 100 && Hospital_Administrator.receptionistIdCount >= 10) {
                receptionistId += "R00" + Hospital_Administrator.receptionistIdCount + "/15";
            } else if (Hospital_Administrator.receptionistIdCount < 1000 && Hospital_Administrator.receptionistIdCount >= 100) {
                receptionistId += "R0" + Hospital_Administrator.receptionistIdCount + "/15";
            }
            Receptionist receptionist = new Receptionist(name, age, phone, gender, Hospital_Administrator.defaultPassword, receptionistId, Double.parseDouble(salary));
            hospitalAdministrator.getReceptionistList().add(receptionist);
            try {
                Hospital_Administrator.switcher = 'R';
                hospitalAdministrator.switchScene("ReceptionistManagementScene.fxml");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("RECEPTIONIST ADDED SUCCESSFULLY");
                alert.setHeaderText("RECEPTIONIST HAS BEEN ADDED SUCCESSFULLY");
                alert.setContentText("THE RECEPTIONIST HAS BEEN ADDED SUCCESSFULLY TO THE LIST WITH THE ID OF " + receptionistId);
                alert.showAndWait();
            } catch (Exception e) {
                System.out.println(e.getMessage());
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
