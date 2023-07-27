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

public class ScheduleAppointmentController {
    Hospital_Administrator hospitalAdministrator=new Hospital_Administrator();
    @FXML
    private TableView<Doctor> availableDoctorTable;

    @FXML
    private DatePicker datePickerDate;

    @FXML
    private TableColumn<Doctor, String> doctorIdColumn;

    @FXML
    private TableColumn<Doctor, String> doctorNameColumn;

    @FXML
    private TableColumn<Doctor, String> doctorSpecialityColumn;

    @FXML
    private ComboBox<Integer> hourComboBox;

    @FXML
    private TextField idTextField;

    @FXML
    private ComboBox<Integer> minuteComboBox;

    @FXML
    private ComboBox<Integer> secondComboBox;

    @FXML
    void goBackOnAction(ActionEvent event) {
        try {
            Hospital_Administrator.switcher='p';
            hospitalAdministrator.switchScene("pAppointmentsManagementScene.fxml");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    void scheduleAppointmentOnAction(ActionEvent event) {
       String doctorId=idTextField.getText();
       LocalDate localDate=datePickerDate.getValue();
        if (doctorId.length()==0 || localDate == null|| hourComboBox.getValue()==null ||minuteComboBox.getValue()==null || secondComboBox.getValue()==null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("DATA MISSING");
            alert.setHeaderText("SOME DATA IS MISSING");
            alert.setContentText("YOU HAVE TO PROVIDE ALL DATA'S\nMAKE SURE EVERYTHING IS NOT EMPTY!!");
            alert.showAndWait();
        }
        else{
            int hour=hourComboBox.getValue();
            int minute=minuteComboBox.getValue();
            int second=secondComboBox.getValue();
            LocalTime localTime=LocalTime.of(hour,minute,second);
            int index=0;
            boolean found=false;
            for (int i=0;i<hospitalAdministrator.getDoctorList().size();i++){
                if (doctorId.equals(hospitalAdministrator.getDoctorList().get(i).getStaffId())){
                    index=i;
                    found=true;
                    break;
                }
            }
            if (found){
                double bill=hospitalAdministrator.getPatientList().get(PatientLoginController.index).getBill();
                if(hospitalAdministrator.getDoctorList().get(index).getSpeciality().equals("General Practitioner")){
                 bill+=500;
                } else if (hospitalAdministrator.getDoctorList().get(index).getSpeciality().equals("Surgery")) {
                    bill+=1500;
                }else if (hospitalAdministrator.getDoctorList().get(index).getSpeciality().equals("Pediatrics")) {
                    bill+=1200;
                }else if (hospitalAdministrator.getDoctorList().get(index).getSpeciality().equals("Cardiology")) {
                    bill+=2500;
                }else if (hospitalAdministrator.getDoctorList().get(index).getSpeciality().equals("Dentistry")) {
                    bill+=800;
                }
                hospitalAdministrator.getPatientList().get(PatientLoginController.index).setBill(bill);
                String appointmentId = "";
                Hospital_Administrator.appointmentIdCount++;
                if (Hospital_Administrator.appointmentIdCount < 10) {
                    appointmentId += "AP000" + Hospital_Administrator.appointmentIdCount + "/15";
                } else if (Hospital_Administrator.appointmentIdCount < 100 && Hospital_Administrator.appointmentIdCount >= 10) {
                    appointmentId += "AP00" + Hospital_Administrator.appointmentIdCount + "/15";
                } else if (Hospital_Administrator.appointmentIdCount < 1000 && Hospital_Administrator.appointmentIdCount >= 100) {
                    appointmentId += "AP0" + Hospital_Administrator.appointmentIdCount + "/15";
                }
                Appointment appointment=new Appointment(appointmentId,localDate,localTime);
                appointment.setPatient(hospitalAdministrator.getPatientList().get(PatientLoginController.index));
                appointment.setDoctor(hospitalAdministrator.getDoctorList().get(index));
                hospitalAdministrator.getPatientList().get(PatientLoginController.index).getAppointments().add(appointment);
                hospitalAdministrator.getDoctorList().get(index).getDoctorAppointments().add(appointment);
                try {
                    Hospital_Administrator.switcher='p';
                    hospitalAdministrator.switchScene("pAppointmentsManagementScene.fxml");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("APPOINTMENT SCHEDULED SUCCESSFULLY");
                    alert.setHeaderText("APPOINTMENT HAS BEEN SCHEDULED SUCCESSFULLY");
                    alert.setContentText("THE APPOINTMENT HAS BEEN ADDED SUCCESSFULLY TO THE LIST WITH THE ID OF " + appointmentId);
                    alert.showAndWait();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            }
            else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("DOCTOR ID NOT FOUND");
                alert.setHeaderText("DOCTOR ID IS NOT FOUND");
                alert.setContentText("PLEASE PROVIDE THE DOCTOR ID FROM THE AVAILABLE DOCTOR'S TABLE!!");
                alert.showAndWait();
            }
        }
    }

    @FXML
    public void initialize(){
        TextFormatter<String> textFormatter = new TextFormatter<>(change -> {
            if (!change.getText().matches("[D0-9/]+") &&change.getText().length()<=8) {
                change.setText("");
            }
            return change;
        });
        datePickerDate.setDayCellFactory(d ->
                new DateCell() {
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
        idTextField.setTextFormatter(textFormatter);
        doctorNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        doctorIdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStaffId()));
        doctorSpecialityColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSpeciality()));
        availableDoctorTable.setItems(hospitalAdministrator.getDoctorList());
    }

}
