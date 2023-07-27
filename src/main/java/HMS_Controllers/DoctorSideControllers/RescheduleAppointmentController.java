package HMS_Controllers.DoctorSideControllers;

import HMS_Classes.Appointment;
import HMS_Controllers.Hospital_Administrator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class RescheduleAppointmentController {
    Hospital_Administrator hospitalAdministrator=new Hospital_Administrator();
    private int patientAppointmentIndex;
    private int doctorAppointmentIndex;
    private int patientIndex;
    @FXML
    private TextField idTextField1=new TextField();
    @FXML
    private DatePicker datePickerDate=new DatePicker();

    @FXML
    private ComboBox<Integer> hourComboBox=new ComboBox<>();

    @FXML
    private ComboBox<Integer> minuteComboBox=new ComboBox<>();

    @FXML
    private ComboBox<Integer> secondComboBox=new ComboBox<>();

    @FXML
    void goBackOnAction(ActionEvent event) {
        try {
            Hospital_Administrator.switcher = 'd';
            hospitalAdministrator.switchScene("dAppointmentsManagementScene.fxml");
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
            for (int i = 0; i<hospitalAdministrator.getDoctorList().get(DoctorLoginController.index).getDoctorAppointments().size(); i++){
                if (appointmentId.equals(hospitalAdministrator.getDoctorList().get(DoctorLoginController.index).getDoctorAppointments().get(i).getAppointmentId())){
                    doctorAppointmentIndex=i;
                    found=true;
                    break;
                }
            }
            if (found) {
                try {
                    Hospital_Administrator.switcher = 'd';
                    hospitalAdministrator.switchScene("rescheduleAppointmentScene2.fxml");
                } catch (Exception e) {
                    System.out.println(e);
                }
            }else{
                try {
                    Hospital_Administrator.switcher='d';
                    hospitalAdministrator.switchScene("dAppointmentsManagementScene.fxml");
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
        LocalDate localDate = datePickerDate.getValue();
        if (localDate == null || hourComboBox == null || minuteComboBox.getValue() == null || secondComboBox.getValue() == null) {
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
            String appointmentId=hospitalAdministrator.getDoctorList().get(DoctorLoginController.index).getDoctorAppointments().get(doctorAppointmentIndex).getAppointmentId();
            Appointment appointment=new Appointment(appointmentId,localDate,localTime);
            appointment.setDoctor(hospitalAdministrator.getDoctorList().get(DoctorLoginController.index));
            appointment.setPatient(hospitalAdministrator.getPatientList().get(patientIndex));
            hospitalAdministrator.getDoctorList().get(DoctorLoginController.index).getDoctorAppointments().remove(doctorAppointmentIndex);
            hospitalAdministrator.getPatientList().get(patientIndex).getAppointments().remove(patientAppointmentIndex);
            hospitalAdministrator.getDoctorList().get(DoctorLoginController.index).getDoctorAppointments().add(doctorAppointmentIndex,appointment);
            hospitalAdministrator.getPatientList().get(patientIndex).getAppointments().add(patientAppointmentIndex,appointment);
            try {
                Hospital_Administrator.switcher='d';
                hospitalAdministrator.switchScene("dAppointmentsManagementScene.fxml");

            } catch (Exception e) {
                e.printStackTrace();
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("APPOINTMENT RESCHEDULED SUCCESSFULLY");
            alert.setHeaderText("APPOINTMENT INFORMATION HAS BEEN RESCHEDULED SUCCESSFULLY");
            alert.setContentText("THE APPOINTMENT HAS BEEN UPDATED");
            alert.showAndWait();
        }
    }

    public void initialize() {
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
        datePickerDate.setValue(hospitalAdministrator.getPatientList().get(DoctorLoginController.index).getAppointments().get(patientAppointmentIndex).getDate());
        hourComboBox.setValue(hospitalAdministrator.getPatientList().get(DoctorLoginController.index).getAppointments().get(patientAppointmentIndex).getLocalTime().getHour());
        minuteComboBox.setValue(hospitalAdministrator.getPatientList().get(DoctorLoginController.index).getAppointments().get(patientAppointmentIndex).getLocalTime().getMinute());
        secondComboBox.setValue(hospitalAdministrator.getPatientList().get(DoctorLoginController.index).getAppointments().get(patientAppointmentIndex).getLocalTime().getSecond());

    }


}
