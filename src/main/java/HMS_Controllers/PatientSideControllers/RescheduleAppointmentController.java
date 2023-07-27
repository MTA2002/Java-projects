package HMS_Controllers.PatientSideControllers;

import HMS_Classes.Appointment;
import HMS_Classes.Doctor;
import HMS_Controllers.Hospital_Administrator;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class RescheduleAppointmentController {
    private static String previousDoctorId = "";
    Hospital_Administrator hospitalAdministrator = new Hospital_Administrator();
    private int patientAppointmentIndex;
    private int doctorAppointmentIndex;
    private int doctorIndex;
    @FXML
    private TableView<Doctor> availableDoctorTable = new TableView<>();

    @FXML
    private DatePicker datePickerDate = new DatePicker();

    @FXML
    private TableColumn<Doctor, String> doctorIdColumn = new TableColumn<>();

    @FXML
    private TableColumn<Doctor, String> doctorNameColumn = new TableColumn<>();

    @FXML
    private TableColumn<Doctor, String> doctorSpecialityColumn = new TableColumn<>();

    @FXML
    private ComboBox<Integer> hourComboBox = new ComboBox<>();

    @FXML
    private TextField idTextField1 = new TextField();

    @FXML
    private ComboBox<Integer> minuteComboBox = new ComboBox<>();

    @FXML
    private ComboBox<Integer> secondComboBox = new ComboBox<>();
    @FXML
    private TextField idTextField2 = new TextField();

    @FXML
    void goBackOnAction(ActionEvent event) {
        try {
            Hospital_Administrator.switcher = 'p';
            hospitalAdministrator.switchScene("pAppointmentsManagementScene.fxml");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    void rescheduleAppointmentOnAction(ActionEvent event) {
        String appointmentId = idTextField1.getText();
        if (appointmentId.length() == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ID MISSING");
            alert.setHeaderText("ID IS MISSING");
            alert.setContentText("PLEASE PROVIDE THE ID OF THE APPOINTMENT YOU WANT TO RESCHEDULE!!");
            alert.setContentText("PLEASE PROVIDE THE ID OF THE APPOINTMENT YOU WANT TO RESCHEDULE!!");
            alert.showAndWait();
        } else {
            boolean found = false;
            for (int i = 0; i < hospitalAdministrator.getPatientList().get(PatientLoginController.index).getAppointments().size(); i++) {
                if (appointmentId.equals(hospitalAdministrator.getPatientList().get(PatientLoginController.index).getAppointments().get(i).getAppointmentId())) {
                    patientAppointmentIndex = i;
                    found = true;
                    break;
                }
            }
            if (found) {
                try {
                    Hospital_Administrator.switcher = 'p';
                    hospitalAdministrator.switchScene("rescheduleAppointmentScene2.fxml");
                } catch (Exception e) {
                    System.out.println(e);
                }
            } else {
                try {
                    Hospital_Administrator.switcher = 'p';
                    hospitalAdministrator.switchScene("pAppointmentsManagementScene.fxml");
                } catch (Exception e) {
                    System.out.println(e);
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("RECORD NOT FOUND");
                alert.setContentText("THE APPOINTMENT YOU ARE LOOKING FOR IS NOT IN THE RECORDS!!!");
                alert.showAndWait();
            }
        }
    }

    @FXML
    void reScheduleAppointmentOnAction2(ActionEvent event) {
        String appointmentId1 = idTextField1.getText();
        previousDoctorId = hospitalAdministrator.getPatientList().get(PatientLoginController.index).getAppointments().get(patientAppointmentIndex).getDoctor().getStaffId();
        for (int i = 0; i < hospitalAdministrator.getDoctorList().size(); i++) {
            if (previousDoctorId.equals(hospitalAdministrator.getDoctorList().get(i).getStaffId())) {
                doctorIndex = i;
                break;
            }
        }
        for (int i = 0; i < hospitalAdministrator.getDoctorList().get(doctorIndex).getDoctorAppointments().size(); i++) {
            if (appointmentId1.equals(hospitalAdministrator.getDoctorList().get(doctorIndex).getDoctorAppointments().get(i).getAppointmentId())) {
                doctorAppointmentIndex = i;
                break;
            }
        }
        String currentDoctorId = idTextField2.getText();
        LocalDate localDate = datePickerDate.getValue();
        if (currentDoctorId.length() == 0 || localDate == null || hourComboBox.getValue() == null || minuteComboBox.getValue() == null || secondComboBox.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("DATA MISSING");
            alert.setHeaderText("SOME DATA IS MISSING");
            alert.setContentText("YOU HAVE TO PROVIDE ALL DATA'S\nMAKE SURE EVERYTHING IS NOT EMPTY!!");
            alert.showAndWait();
        } else {
            int hour = hourComboBox.getValue();
            int minute = minuteComboBox.getValue();
            int second = secondComboBox.getValue();
            LocalTime localTime = LocalTime.of(hour, minute, second);
            int index = 0;
            boolean found = false;
            for (int i = 0; i < hospitalAdministrator.getDoctorList().size(); i++) {
                if (currentDoctorId.equals(hospitalAdministrator.getDoctorList().get(i).getStaffId())) {
                    index = i;
                    found = true;
                    break;
                }
            }
            if (found) {
                double bill = hospitalAdministrator.getPatientList().get(PatientLoginController.index).getBill();
                if (hospitalAdministrator.getDoctorList().get(doctorIndex).getSpeciality().equals("General Practitioner")) {
                    bill -= 500;
                } else if (hospitalAdministrator.getDoctorList().get(doctorIndex).getSpeciality().equals("Surgery")) {
                    bill -= 1500;
                } else if (hospitalAdministrator.getDoctorList().get(doctorIndex).getSpeciality().equals("Pediatrics")) {
                    bill -= 1200;
                } else if (hospitalAdministrator.getDoctorList().get(doctorIndex).getSpeciality().equals("Cardiology")) {
                    bill -= 2500;
                } else if (hospitalAdministrator.getDoctorList().get(doctorIndex).getSpeciality().equals("Dentistry")) {
                    bill -= 800;
                }

                if (hospitalAdministrator.getDoctorList().get(index).getSpeciality().equals("General Practitioner")) {
                    bill += 500;
                } else if (hospitalAdministrator.getDoctorList().get(index).getSpeciality().equals("Surgery")) {
                    bill += 1500;
                } else if (hospitalAdministrator.getDoctorList().get(index).getSpeciality().equals("Pediatrics")) {
                    bill += 1200;
                } else if (hospitalAdministrator.getDoctorList().get(index).getSpeciality().equals("Cardiology")) {
                    bill += 2500;
                } else if (hospitalAdministrator.getDoctorList().get(index).getSpeciality().equals("Dentistry")) {
                    bill += 800;
                }
                hospitalAdministrator.getPatientList().get(PatientLoginController.index).setBill(bill);
                String appointmentId = hospitalAdministrator.getPatientList().get(PatientLoginController.index).getAppointments().get(patientAppointmentIndex).getAppointmentId();
                Appointment appointment = new Appointment(appointmentId, localDate, localTime);
                appointment.setPatient(hospitalAdministrator.getPatientList().get(PatientLoginController.index));
                appointment.setDoctor(hospitalAdministrator.getDoctorList().get(index));
                hospitalAdministrator.getPatientList().get(PatientLoginController.index).getAppointments().remove(patientAppointmentIndex);
                hospitalAdministrator.getDoctorList().get(doctorIndex).getDoctorAppointments().remove(doctorAppointmentIndex);
                hospitalAdministrator.getPatientList().get(PatientLoginController.index).getAppointments().add(patientAppointmentIndex, appointment);

                if (previousDoctorId.equals(currentDoctorId)) {
                    hospitalAdministrator.getDoctorList().get(doctorIndex).getDoctorAppointments().add(doctorAppointmentIndex, appointment);
                } else {
                    hospitalAdministrator.getDoctorList().get(index).getDoctorAppointments().add(appointment);
                }
                try {
                    Hospital_Administrator.switcher = 'p';
                    hospitalAdministrator.switchScene("pAppointmentsManagementScene.fxml");

                } catch (Exception e) {
                    e.printStackTrace();
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("APPOINTMENT RESCHEDULED SUCCESSFULLY");
                alert.setHeaderText("APPOINTMENT INFORMATION HAS BEEN RESCHEDULED SUCCESSFULLY");
                alert.setContentText("THE APPOINTMENT HAS BEEN UPDATED");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("DOCTOR ID NOT FOUND");
                alert.setHeaderText("DOCTOR ID IS NOT FOUND");
                alert.setContentText("PLEASE PROVIDE THE DOCTOR ID FROM THE AVAILABLE DOCTOR'S TABLE!!");
                alert.showAndWait();
            }
        }
    }

    public void initialize() {
        TextFormatter<String> textFormatter = new TextFormatter<>(change -> {
            if (!change.getText().matches("[D0-9/]+") && change.getText().length() <= 8) {
                change.setText("");
            }
            return change;
        });
        datePickerDate.setDayCellFactory(d -> new DateCell() {
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                setDisable(empty || item.isBefore(LocalDate.now()));
            }
        });
        for (int i = LocalTime.now().getHour(); i < 24; i++) {
            hourComboBox.getItems().add(i);
        }
        for (int i = LocalTime.now().getMinute(); i < 60; i++) {
            minuteComboBox.getItems().add(i);
        }
        for (int i = LocalTime.now().getSecond(); i < 60; i++) {
            secondComboBox.getItems().add(i);
        }
        idTextField2.setTextFormatter(textFormatter);
        idTextField2.setText(hospitalAdministrator.getPatientList().get(PatientLoginController.index).getAppointments().get(patientAppointmentIndex).getDoctor().getStaffId());
        datePickerDate.setValue(hospitalAdministrator.getPatientList().get(PatientLoginController.index).getAppointments().get(patientAppointmentIndex).getDate());
        hourComboBox.setValue(hospitalAdministrator.getPatientList().get(PatientLoginController.index).getAppointments().get(patientAppointmentIndex).getLocalTime().getHour());
        minuteComboBox.setValue(hospitalAdministrator.getPatientList().get(PatientLoginController.index).getAppointments().get(patientAppointmentIndex).getLocalTime().getMinute());
        secondComboBox.setValue(hospitalAdministrator.getPatientList().get(PatientLoginController.index).getAppointments().get(patientAppointmentIndex).getLocalTime().getSecond());
        doctorNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        doctorIdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStaffId()));
        doctorSpecialityColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSpeciality()));
        availableDoctorTable.setItems(hospitalAdministrator.getDoctorList());
    }

}
