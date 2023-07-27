package HMS_Controllers.PatientSideControllers;

import HMS_Classes.Appointment;
import HMS_Controllers.Hospital_Administrator;
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
    private TableColumn<Appointment, String> doctorNameColumn;

    @FXML
    private TableColumn<Appointment, String> doctorSpecialityColumn;

    @FXML
    private TableColumn<Appointment, LocalTime> timeOfAppointmentColumn;

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
    public void initialize(){
        appointmentIdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAppointmentId()));
        doctorNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDoctor().getName()));
        doctorSpecialityColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDoctor().getSpeciality()));
        dateOfAppointmentColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDate()));
        timeOfAppointmentColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getLocalTime()));
        appointmentTable.setItems(hospitalAdministrator.getPatientList().get(PatientLoginController.index).getAppointments());
    }

}
