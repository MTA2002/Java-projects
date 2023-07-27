package HMS_Controllers.DoctorControllers;

import HMS_Classes.Doctor;
import HMS_Controllers.Hospital_Administrator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.function.UnaryOperator;

public class UpdateDoctorController2 {
    Hospital_Administrator hospitalAdministrator=new Hospital_Administrator();

    private String gender;
    @FXML
    private TextField nameTextField = new TextField();
    @FXML
    private RadioButton femaleRadioButton = new RadioButton();

    @FXML
    private RadioButton maleRadioButton = new RadioButton();
    @FXML
    private ChoiceBox<Integer> ageChoiceBox = new ChoiceBox<>();
    @FXML
    private TextField phoneTextField = new TextField();

    @FXML
    private TextField salaryTextField = new TextField();

    @FXML
    private ChoiceBox<String> specialityChoiceBox = new ChoiceBox<>();
    @FXML
    void updateDoctorOnAction(ActionEvent event) {
        String name = nameTextField.getText();
        Integer age = ageChoiceBox.getValue();
        String phone = phoneTextField.getText();
        String salary = salaryTextField.getText();
        String speciality = specialityChoiceBox.getValue();
        if (name.length() == 0 || age == null || phone.length() == 0 || salary.length() == 0 || speciality == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("DATA MISSING");
            alert.setHeaderText("SOME DATA IS MISSING");
            alert.setContentText("YOU HAVE TO PROVIDE ALL DATA'S\nMAKE SURE EVERYTHING IS NOT EMPTY!!");
            alert.showAndWait();
        } else {
            if (maleRadioButton.isSelected()) {
                gender = "Male";
            } else {
               gender = "Female";
            }
            Doctor doctor = new Doctor(name, age, phone, gender, hospitalAdministrator.getDoctorList().get(UpdateDoctorController.index).getPassword(), hospitalAdministrator.getDoctorList().get(UpdateDoctorController.index).getStaffId(), Double.parseDouble(salary), speciality);
            doctor.setDoctorAppointments(hospitalAdministrator.getDoctorList().get(UpdateDoctorController.index).getDoctorAppointments());
            for(int i=0;i<hospitalAdministrator.getDoctorList().get(UpdateDoctorController.index).getDoctorAppointments().size();i++){
                hospitalAdministrator.getDoctorList().get(UpdateDoctorController.index).getDoctorAppointments().get(i).setDoctor(doctor);
            }
            for (int i=0;i<hospitalAdministrator.getPatientList().size();i++){
                for(int j=0;j<hospitalAdministrator.getPatientList().get(i).getAppointments().size();j++){
                    if(UpdateDoctorController.id1.equals(hospitalAdministrator.getPatientList().get(i).getAppointments().get(j).getDoctor().getStaffId())){
                        hospitalAdministrator.getPatientList().get(i).getAppointments().get(j).setDoctor(doctor);
                    }
                }
            }
            hospitalAdministrator.getDoctorList().remove(UpdateDoctorController.index);
            hospitalAdministrator.getDoctorList().add(UpdateDoctorController.index, doctor);


            try {
                Hospital_Administrator.switcher = 'D';
                hospitalAdministrator.switchScene("doctorManagementScene.fxml");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("DOCTOR UPDATED SUCCESSFULLY");
                alert.setHeaderText("DOCTOR INFORMATION HAS BEEN UPDATED SUCCESSFULLY");
                alert.setContentText("THE DOCTOR HAS BEEN UPDATED");
                alert.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void goBackOnAction(ActionEvent event) {
        try {
            Hospital_Administrator.switcher = 'D';
            hospitalAdministrator.switchScene("doctorManagementScene.fxml");
        } catch (Exception e) {
            e.printStackTrace();
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
        TextFormatter<String> phoneFormatter = new TextFormatter<>(phoneFilter);
        phoneTextField.setTextFormatter(phoneFormatter);
        specialityChoiceBox.getItems().add("General Practitioner");
        specialityChoiceBox.getItems().add("Surgery");
        specialityChoiceBox.getItems().add("Pediatrics");
        specialityChoiceBox.getItems().add("Cardiology");
        specialityChoiceBox.getItems().add("Dentistry");
        UnaryOperator<TextFormatter.Change> salaryFilter = change -> {
            String text = change.getText();
            if (text.matches("[0-9.]*")) {
                return change;
            }
            return null;
        };
        TextFormatter<String> salaryFormatter = new TextFormatter<>(salaryFilter);
        salaryTextField.setTextFormatter(salaryFormatter);
        nameTextField.setText(hospitalAdministrator.getDoctorList().get(UpdateDoctorController.index).getName());
        ageChoiceBox.setValue(hospitalAdministrator.getDoctorList().get(UpdateDoctorController.index).getAge());
        phoneTextField.setText(hospitalAdministrator.getDoctorList().get(UpdateDoctorController.index).getPhone());
        salaryTextField.setText(String.valueOf(hospitalAdministrator.getDoctorList().get(UpdateDoctorController.index).getSalary()));
        specialityChoiceBox.setValue(hospitalAdministrator.getDoctorList().get(UpdateDoctorController.index).getSpeciality());
        ToggleGroup toggleGroup = new ToggleGroup();
        maleRadioButton.setToggleGroup(toggleGroup);
        femaleRadioButton.setToggleGroup(toggleGroup);
        if (hospitalAdministrator.getDoctorList().get(UpdateDoctorController.index).getGender().equals("Male")) {
            maleRadioButton.setSelected(true);
        } else {
            femaleRadioButton.setSelected(true);
        }
    }

}
