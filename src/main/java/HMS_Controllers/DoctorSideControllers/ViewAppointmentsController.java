package HMS_Controllers.DoctorSideControllers;

import HMS_Classes.Appointment;
import HMS_Controllers.Hospital_Administrator;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.time.LocalDate;
import java.time.LocalTime;

public class ViewAppointmentsController {
    Hospital_Administrator hospitalAdministrator=new Hospital_Administrator();
    @FXML
    private TableColumn<Appointment, String> appointmentIdColumn;

    @FXML
    private TableView<Appointment> appointmentTable;

    @FXML
    private TableColumn<Appointment, LocalDate> dateOfAppointmentColumn;

    @FXML
    private TableColumn<Appointment, Integer> patientAge;

    @FXML
    private TableColumn<Appointment, String> patientGender;

    @FXML
    private TableColumn<Appointment, String> patientName;

    @FXML
    private TableColumn<Appointment, LocalTime> timeOfAppointmentColumn;

    @FXML
    void goBackOnAction(ActionEvent event) {
        try {
            Hospital_Administrator.switcher='d';
            hospitalAdministrator.switchScene("dAppointmentsManagementScene.fxml");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    public void initialize(){
        appointmentIdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAppointmentId()));
        patientName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPatient().getName()));
        patientAge.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPatient().getAge()));
        patientGender.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPatient().getGender()));
        dateOfAppointmentColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDate()));
        timeOfAppointmentColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getLocalTime()));
        appointmentTable.setItems(hospitalAdministrator.getDoctorList().get(DoctorLoginController.index).getDoctorAppointments());

    }

}
