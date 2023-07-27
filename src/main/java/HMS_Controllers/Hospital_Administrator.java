package HMS_Controllers;

import HMS_Classes.*;
import HMS_Controllers.DoctorControllers.DoctorManagementController;
import HMS_Controllers.DoctorSideControllers.DoctorLoginController;
import HMS_Controllers.PatientSideControllers.PatientLoginController;
import HMS_Controllers.ReceptionistSideControllers.ReceptionistLoginController;
import HMS_Controllers.ReceptionistControllers.ReceptionistManagementController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Hospital_Administrator extends Application {

    public static char switcher = 'A';
    public static int doctorIdCount = 0, receptionistIdCount = 0, patientIdCount = 0,appointmentIdCount=0,prescriptionIdCount=0;
    public static String defaultPassword = "12345678";
    protected static ArrayList<Admin> admins = new ArrayList<>();
    private static  ObservableList<Doctor> doctorList = FXCollections.observableArrayList();

    private static  ObservableList<Receptionist> receptionistList = FXCollections.observableArrayList();
    private static  ObservableList<Patient> patientList = FXCollections.observableArrayList();
    private static  ObservableList<Doctor> foundDoctor = FXCollections.observableArrayList();
    private static  ObservableList<Receptionist> foundReceptionist = FXCollections.observableArrayList();
    private static  ObservableList<Patient> foundPatient = FXCollections.observableArrayList();
    private static Stage primaryStage;
    public ObservableList<Doctor> getFoundDoctorList() {
        return foundDoctor;
    }

    public ObservableList<Doctor> getDoctorList() {
        return doctorList;
    }

    public ObservableList<Receptionist> getFoundReceptionistList() {
        return foundReceptionist;
    }

    public ObservableList<Receptionist> getReceptionistList() {
        return receptionistList;
    }
    public ObservableList<Patient> getFoundPatientList() {
        return foundPatient;
    }
    public ObservableList<Patient> getPatientList() {
        return patientList;
    }

    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Hospital_Administrator.class.getResource("HomeScene.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Hospital Management System");
            stage.setScene(scene);
            primaryStage = stage;
            primaryStage.setResizable(false);
            primaryStage.setMaxHeight(550);
            primaryStage.setMinHeight(550);
            primaryStage.setMinWidth(800);
            primaryStage.setMaxWidth(800);
            stage.show();
        } catch (IOException e) {
            System.out.println(e);
        }


    }

    public void switchScene(String fxmlFile) throws Exception {
            FXMLLoader fxmlLoader = new FXMLLoader();
        switch (switcher) {
            case 'D':
                fxmlLoader = new FXMLLoader(DoctorManagementController.class.getResource(fxmlFile));
                break;
            case 'A':
                fxmlLoader = new FXMLLoader(Hospital_Administrator.class.getResource(fxmlFile));
                break;
            case 'R':
                fxmlLoader = new FXMLLoader(ReceptionistManagementController.class.getResource(fxmlFile));
                break;
            case 'r':
                fxmlLoader = new FXMLLoader(ReceptionistLoginController.class.getResource(fxmlFile));
                break;
            case 'p':
                fxmlLoader = new FXMLLoader(PatientLoginController.class.getResource(fxmlFile));
                break;
            case 'd':
                fxmlLoader = new FXMLLoader(DoctorLoginController.class.getResource(fxmlFile));
                break;
            default:
                break;
        }
            Scene scene = new Scene(fxmlLoader.load());
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.setMaxHeight(550);
            primaryStage.setMinHeight(550);
            primaryStage.setMinWidth(800);
            primaryStage.setMaxWidth(800);
            primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}