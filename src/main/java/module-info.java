module com.example.hospital_management_system_2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires java.desktop;
    opens HMS_Controllers to javafx.fxml;
    exports HMS_Controllers;
    exports HMS_Controllers.DoctorControllers;
    exports HMS_Controllers.ReceptionistControllers;
    exports HMS_Controllers.ReceptionistSideControllers;
    exports HMS_Controllers.PatientSideControllers;
    exports HMS_Controllers.DoctorSideControllers;
    opens HMS_Controllers.DoctorControllers to javafx.fxml;
    opens HMS_Controllers.ReceptionistControllers to javafx.fxml;
    opens HMS_Controllers.ReceptionistSideControllers to javafx.fxml;
    opens HMS_Controllers.PatientSideControllers to javafx.fxml;
    opens HMS_Controllers.DoctorSideControllers to javafx.fxml;
}