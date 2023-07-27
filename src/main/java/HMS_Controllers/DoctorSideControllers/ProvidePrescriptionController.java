package HMS_Controllers.DoctorSideControllers;

import HMS_Classes.Prescription;
import HMS_Controllers.Hospital_Administrator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.function.UnaryOperator;

public class ProvidePrescriptionController {
    Hospital_Administrator hospitalAdministrator = new Hospital_Administrator();
    private int patientAppointmentIndex;
    private int doctorAppointmentIndex;
    private int patientIndex;

    private static String appointmentId;
    @FXML
    private TextField idTextField = new TextField();

    @FXML
    private DatePicker dateOfPrescription = new DatePicker();

    @FXML
    private TextField doseTextField = new TextField();

    @FXML
    private ChoiceBox<Integer> intakeChoiceBox = new ChoiceBox<>();

    @FXML
    private TextField medicineNameTextField = new TextField();

    @FXML
    void prescribeOnAction(ActionEvent event) {
        appointmentId = idTextField.getText();
        if (appointmentId.length() == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ID MISSING");
            alert.setHeaderText("ID IS MISSING");
            alert.setContentText("PLEASE PROVIDE THE ID OF THE PATIENT YOU WANT TO PRESCRIBE!!");
            alert.showAndWait();
        } else {
            boolean found = false;
            for (int i = 0; i < hospitalAdministrator.getDoctorList().get(DoctorLoginController.index).getDoctorAppointments().size(); i++) {
                if (appointmentId.equals(hospitalAdministrator.getDoctorList().get(DoctorLoginController.index).getDoctorAppointments().get(i).getAppointmentId())) {
                    doctorAppointmentIndex = i;
                    found = true;
                }
            }
            if (found) {
                try {
                    Hospital_Administrator.switcher = 'd';
                    hospitalAdministrator.switchScene("providePrescriptionScene2.fxml");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else {
                try {
                    Hospital_Administrator.switcher = 'd';
                    hospitalAdministrator.switchScene("doctorSideManagementScene.fxml");
                } catch (Exception e) {
                    System.out.println(e);
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("PATIENT NOT FOUND");
                alert.setContentText("THE PATIENT HAS TO HAVE AN APPOINTMENT WITH YOU!!!");
                alert.showAndWait();
            }
        }

    }

    @FXML
    void confirmPrescriptionOnAction(ActionEvent event) {
        String medicineName = medicineNameTextField.getText();
        LocalDate localDate = dateOfPrescription.getValue();
        Integer dose = Integer.parseInt(doseTextField.getText());
        Integer durationIntake = intakeChoiceBox.getValue();
        if (medicineName.length() == 0 || localDate == null || doseTextField.getText().length() == 0 || durationIntake == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("DATA MISSING");
            alert.setHeaderText("SOME DATA IS MISSING");
            alert.setContentText("YOU HAVE TO PROVIDE ALL DATA'S\nMAKE SURE EVERYTHING IS NOT EMPTY!!");
            alert.showAndWait();
        } else {
            boolean found2=false;
            for (int i = 0; i < hospitalAdministrator.getPatientList().size(); i++) {
                for (int j = 0; j < hospitalAdministrator.getPatientList().get(i).getAppointments().size(); j++) {
                    if (hospitalAdministrator.getPatientList().get(i).getAppointments().get(j).getAppointmentId().equals(appointmentId)) {
                        System.out.println("HELLO!!!");
                        patientIndex = i;
                        patientAppointmentIndex = j;
                        found2=true;
                        break;
                    }
                }
            }
            String prescriptionId = "";
            Hospital_Administrator.prescriptionIdCount++;
            if (Hospital_Administrator.prescriptionIdCount < 10) {
                prescriptionId += "PR000" + Hospital_Administrator.prescriptionIdCount + "/15";
            } else if (Hospital_Administrator.prescriptionIdCount < 100 && Hospital_Administrator.prescriptionIdCount >= 10) {
                prescriptionId += "PR00" + Hospital_Administrator.prescriptionIdCount + "/15";
            } else if (Hospital_Administrator.prescriptionIdCount < 1000 && Hospital_Administrator.prescriptionIdCount >= 100) {
                prescriptionId += "PR0" + Hospital_Administrator.prescriptionIdCount + "/15";
            }

            double bill = hospitalAdministrator.getPatientList().get(patientIndex).getBill();
            if (hospitalAdministrator.getDoctorList().get(DoctorLoginController.index).getSpeciality().equals("General Practitioner")) {
                bill -= 500;
            } else if (hospitalAdministrator.getDoctorList().get(DoctorLoginController.index).getSpeciality().equals("Surgery")) {
                bill -= 1500;
            } else if (hospitalAdministrator.getDoctorList().get(DoctorLoginController.index).getSpeciality().equals("Pediatrics")) {
                bill -= 1200;
            } else if (hospitalAdministrator.getDoctorList().get(DoctorLoginController.index).getSpeciality().equals("Cardiology")) {
                bill -= 2500;
            } else if (hospitalAdministrator.getDoctorList().get(DoctorLoginController.index).getSpeciality().equals("Dentistry")) {
                bill -= 800;
            }
            hospitalAdministrator.getPatientList().get(patientIndex).setBill(bill);

            String doctorName = hospitalAdministrator.getDoctorList().get(DoctorLoginController.index).getName();
            String patientName = hospitalAdministrator.getPatientList().get(patientIndex).getName();
            Prescription prescription = new Prescription(prescriptionId, doctorName, patientName, localDate, medicineName, dose, durationIntake);
            hospitalAdministrator.getPatientList().get(patientIndex).getPrescriptions().add(prescription);
            hospitalAdministrator.getPatientList().get(patientIndex).getAppointments().remove(patientAppointmentIndex);
            hospitalAdministrator.getDoctorList().get(DoctorLoginController.index).getDoctorAppointments().remove(doctorAppointmentIndex);

            try {
                Hospital_Administrator.switcher = 'd';
                hospitalAdministrator.switchScene("doctorSideManagementScene.fxml");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("PRESCRIPTION ISSUED SUCCESSFULLY");
                alert.setHeaderText("PRESCRIPTION HAS BEEN ISSUED SUCCESSFULLY");
                alert.setContentText("THE PRESCRIPTION HAS BEEN ISSUED SUCCESSFULLY TO THE LIST WITH THE ID OF " + prescriptionId);
                alert.showAndWait();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }


    @FXML
    void cancelOnAction(ActionEvent event) {
        try {
            Hospital_Administrator.switcher = 'd';
            hospitalAdministrator.switchScene("doctorSideManagementScene.fxml");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    public void initialize() {
        for (int i = 1; i <= 4; i++) {
            intakeChoiceBox.getItems().add(i);
        }
        UnaryOperator<TextFormatter.Change> doseFilter = change -> {
            String text = change.getText();
            if (text.matches("[0-9]*")) {
                return change;
            }
            return null;
        };
        TextFormatter<String> doseFormatter = new TextFormatter<>(doseFilter);
        doseTextField.setTextFormatter(doseFormatter);
        TextFormatter<String> textFormatter = new TextFormatter<>(change -> {
            if (!change.getText().matches("[a-z A-Z]+")) {
                change.setText("");
            }
            return change;
        });
        medicineNameTextField.setTextFormatter(textFormatter);
        dateOfPrescription.setDayCellFactory(d ->
                new DateCell() {
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        setDisable(empty || item.isBefore(LocalDate.now()));
                    }
                });
    }


}
