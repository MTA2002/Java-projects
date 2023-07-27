package HMS_Controllers.ReceptionistControllers;

import HMS_Classes.Receptionist;
import HMS_Controllers.Hospital_Administrator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.function.UnaryOperator;

public class UpdateReceptionistController {
    Hospital_Administrator hospitalAdministrator = new Hospital_Administrator();
    private int index;
    private boolean found;
    @FXML
    private TextField nameTextField = new TextField();
    @FXML
    private RadioButton femaleRadioButton = new RadioButton();

    @FXML
    private RadioButton maleRadioButton = new RadioButton();
    @FXML
    private ChoiceBox<Integer> ageChoiceBox = new ChoiceBox<>();

    @FXML
    private TextField idTextField = new TextField();

    @FXML
    private TextField phoneTextField = new TextField();

    @FXML
    private TextField salaryTextField = new TextField();


    @FXML
    void updateOnAction(ActionEvent event) {
        String id = idTextField.getText();
        if (id.length() == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ID MISSING");
            alert.setHeaderText("ID IS MISSING");
            alert.setContentText("PLEASE PROVIDE THE ID OF THE RECEPTIONIST YOU WANT TO UPDATE!!");
            alert.showAndWait();
        } else {
            for (int i = 0; i < hospitalAdministrator.getReceptionistList().size(); i++) {
                if (hospitalAdministrator.getReceptionistList().get(i).getStaffId().equals(id)) {
                    found = true;
                    index = i;
                    break;
                }
            }
            if (found) {
                try {
                    Hospital_Administrator.switcher = 'R';
                    hospitalAdministrator.switchScene("updateReceptionistScene.fxml");
                } catch (Exception e) {
                    System.out.println(e);
                }

            } else {
                try {
                    Hospital_Administrator.switcher = 'R';
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
    void updateReceptionistOnAction(ActionEvent event) {
        String name = nameTextField.getText();
        Integer age = ageChoiceBox.getValue();
        String phone = phoneTextField.getText();
        String salary = salaryTextField.getText();

        if (name.length() == 0 || age == null || phone.length() == 0 || salary.length() == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("DATA MISSING");
            alert.setHeaderText("SOME DATA IS MISSING");
            alert.setContentText("YOU HAVE TO PROVIDE ALL DATA'S\nMAKE SURE EVERYTHING IS NOT EMPTY!!");
            alert.showAndWait();
        } else {
            String gender;
            if (maleRadioButton.isSelected()) {
                gender = "Male";
            } else {
                gender = "Female";
            }
            Receptionist receptionist = new Receptionist(name, age, phone, gender, hospitalAdministrator.getReceptionistList().get(index).getPassword(), hospitalAdministrator.getReceptionistList().get(index).getStaffId(), Double.parseDouble(salary));
            hospitalAdministrator.getReceptionistList().remove(index);
            hospitalAdministrator.getReceptionistList().add(index, receptionist);

            try {
                Hospital_Administrator.switcher = 'R';
                hospitalAdministrator.switchScene("ReceptionistManagementScene.fxml");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("RECEPTIONIST UPDATED SUCCESSFULLY");
                alert.setHeaderText("RECEPTIONIST INFORMATION HAS BEEN UPDATED SUCCESSFULLY");
                alert.setContentText("THE RECEPTIONIST HAS BEEN UPDATED");
                alert.showAndWait();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    @FXML
    void goBackOnAction(ActionEvent event) {
        try {
            Hospital_Administrator.switcher = 'R';
            hospitalAdministrator.switchScene("ReceptionistManagementScene.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
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
        TextFormatter<String> phoneFormatter = new TextFormatter<>(phoneFilter);
        phoneTextField.setTextFormatter(phoneFormatter);
        UnaryOperator<TextFormatter.Change> salaryFilter = change -> {
            String text = change.getText();
            if (text.matches("[0-9.]*")) {
                return change;
            }
            return null;
        };
        TextFormatter<String> salaryFormatter = new TextFormatter<>(salaryFilter);
        salaryTextField.setTextFormatter(salaryFormatter);
        nameTextField.setText(hospitalAdministrator.getReceptionistList().get(index).getName());
        ageChoiceBox.setValue(hospitalAdministrator.getReceptionistList().get(index).getAge());
        phoneTextField.setText(hospitalAdministrator.getReceptionistList().get(index).getPhone());
        String salaryString = Double.toString(hospitalAdministrator.getReceptionistList().get(index).getSalary());
        salaryTextField.setText(salaryString);
        ToggleGroup toggleGroup = new ToggleGroup();
        maleRadioButton.setToggleGroup(toggleGroup);
        femaleRadioButton.setToggleGroup(toggleGroup);
        if (hospitalAdministrator.getReceptionistList().get(index).getGender().equals("Male")) {
            maleRadioButton.setSelected(true);
        } else {
            femaleRadioButton.setSelected(true);
        }


    }

}