package HMS_Controllers.PatientSideControllers;

import HMS_Classes.Prescription;
import HMS_Controllers.Hospital_Administrator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ViewPrescriptionController2 {
    Hospital_Administrator hospitalAdministrator=new Hospital_Administrator();
    @FXML
    private Label dateLabel=new Label();

    @FXML
    private Label doctorNameLabel=new Label();

    @FXML
    private Label doseLabel=new Label();

    @FXML
    private Label intakeLabel=new Label();

    @FXML
    private Label medicineNameLabel=new Label();

    @FXML
    private Label patientNameLabel=new Label();
    @FXML
    void cancelOnAction(ActionEvent event) {
        try {
            Hospital_Administrator.switcher='p';
            hospitalAdministrator.switchScene("patientSideManagementScene.fxml");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    @FXML
    public void initialize(){
            Prescription prescription = hospitalAdministrator.getPatientList().get(PatientLoginController.index).getPrescriptions().get(ViewPrescriptionController.prescriptionIndex);
            dateLabel.setText(String.valueOf(prescription.getDate()));
            doctorNameLabel.setText(prescription.getDoctorName());
            patientNameLabel.setText(prescription.getPatientName());
            medicineNameLabel.setText(prescription.getMedicineName());
            doseLabel.setText(String.valueOf(prescription.getDoseOfMedicine()));
            intakeLabel.setText(String.valueOf(prescription.getDuration()));
    }
}
