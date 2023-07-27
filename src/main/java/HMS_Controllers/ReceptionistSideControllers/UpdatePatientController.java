package HMS_Controllers.ReceptionistSideControllers;

import HMS_Classes.Patient;
import HMS_Controllers.Hospital_Administrator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.function.UnaryOperator;

public class UpdatePatientController {
    Hospital_Administrator hospitalAdministrator = new Hospital_Administrator();
    private int index;
    private boolean found;
    @FXML
    private TextField idTextField=new TextField();
    @FXML
    private ChoiceBox<Integer> ageChoiceBox=new ChoiceBox<>();

    @FXML
    private RadioButton femaleRadioButton=new RadioButton();

    @FXML
    private RadioButton maleRadioButton=new RadioButton();

    @FXML
    private TextField nameTextField=new TextField();

    @FXML
    private TextField phoneTextField=new TextField();

    @FXML
    void goBackOnAction(ActionEvent event) {
        try {
            Hospital_Administrator.switcher = 'r';
            hospitalAdministrator.switchScene("patientManagementScene.fxml");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void updateOnAction(ActionEvent event) {
        String id = idTextField.getText();
        if (id.length() == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ID MISSING");
            alert.setHeaderText("ID IS MISSING");
            alert.setContentText("PLEASE PROVIDE THE ID OF THE PATIENT YOU WANT TO UPDATE!!");
            alert.showAndWait();
        } else {
            for (int i = 0; i < hospitalAdministrator.getPatientList().size(); i++) {
                if (hospitalAdministrator.getPatientList().get(i).getPatientID().equals(id)) {
                    found = true;
                    index = i;
                    break;
                }
            }
            if (found) {
                try {
                    Hospital_Administrator.switcher = 'r';
                    hospitalAdministrator.switchScene("updatePatientScene.fxml");
                } catch (Exception e) {
                    System.out.println(e);
                }

            } else {
                try {
                    Hospital_Administrator.switcher = 'r';
                    hospitalAdministrator.switchScene("patientManagementScene.fxml");
                } catch (Exception e) {
                    System.out.println(e);
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("RECORD NOT FOUND");
                alert.setContentText("THE PATIENT YOU ARE LOOKING FOR IS NOT IN THE RECORDS!!!");
                alert.showAndWait();
            }
        }
    }

    @FXML
    void updatePatientOnAction(ActionEvent event) {
        String id = idTextField.getText();
        String name = nameTextField.getText();
        Integer age = ageChoiceBox.getValue();
        String phone = phoneTextField.getText();

        if (name.length() == 0 || age == null || phone.length() == 0) {
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
            Patient patient = new Patient(name, age, phone, gender, hospitalAdministrator.getPatientList().get(index).getPassword(), hospitalAdministrator.getPatientList().get(index).getPatientID(), hospitalAdministrator.getPatientList().get(index).isCheckInStatus());
            patient.setCheckInValue(hospitalAdministrator.getPatientList().get(index).getCheckInValue());
            patient.setBill(hospitalAdministrator.getPatientList().get(index).getBill());
            patient.setAppointments(hospitalAdministrator.getPatientList().get(index).getAppointments());
            for(int i=0;i<hospitalAdministrator.getPatientList().get(index).getAppointments().size();i++){
                hospitalAdministrator.getPatientList().get(index).getAppointments().get(i).setPatient(patient);
            }
            for (int i=0;i<hospitalAdministrator.getDoctorList().size();i++){
                for(int j=0;j<hospitalAdministrator.getDoctorList().get(i).getDoctorAppointments().size();j++){
                    if(id.equals(hospitalAdministrator.getDoctorList().get(i).getDoctorAppointments().get(j).getPatient().getPatientID())){
                        hospitalAdministrator.getDoctorList().get(i).getDoctorAppointments().get(j).setPatient(patient);
                    }
                }
            }
            hospitalAdministrator.getPatientList().remove(index);
            hospitalAdministrator.getPatientList().add(index, patient);

            try {
                Hospital_Administrator.switcher = 'r';
                hospitalAdministrator.switchScene("patientManagementScene.fxml");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("PATIENT UPDATED SUCCESSFULLY");
                alert.setHeaderText("PATIENT INFORMATION HAS BEEN UPDATED SUCCESSFULLY");
                alert.setContentText("THE PATIENT HAS BEEN UPDATED");
                alert.showAndWait();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    @FXML
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
        TextFormatter<String> phoneFormatter = new TextFormatter<>(phoneFilter);
        phoneTextField.setTextFormatter(phoneFormatter);
        nameTextField.setText(hospitalAdministrator.getPatientList().get(index).getName());
        ageChoiceBox.setValue(hospitalAdministrator.getPatientList().get(index).getAge());
        phoneTextField.setText(hospitalAdministrator.getPatientList().get(index).getPhone());
        ToggleGroup toggleGroup = new ToggleGroup();
        maleRadioButton.setToggleGroup(toggleGroup);
        femaleRadioButton.setToggleGroup(toggleGroup);
        if (hospitalAdministrator.getPatientList().get(index).getGender().equals("Male")) {
            maleRadioButton.setSelected(true);
        } else {
            femaleRadioButton.setSelected(true);
        }


    }


}
