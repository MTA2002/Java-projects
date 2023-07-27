package HMS_Controllers.PatientSideControllers;

import HMS_Controllers.Hospital_Administrator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class CancelAppointmentController {
    Hospital_Administrator hospitalAdministrator=new Hospital_Administrator();
    @FXML
    private TextField idTextField;

    @FXML
    void cancelAppointmentOnAction(ActionEvent event) {
        String id = idTextField.getText();
        if (id.length() == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ID MISSING");
            alert.setHeaderText("ID IS MISSING");
            alert.setContentText("PLEASE PROVIDE THE ID OF THE APPOINTMENT YOU WANT TO CANCEL!!");
            alert.setContentText("PLEASE PROVIDE THE ID OF THE APPOINTMENT YOU WANT TO CANCEL!!");
            alert.showAndWait();
        } else {
            int patientAppointmentIndex=0;
            int doctorAppointmentIndex=0;
            int doctorIndex=0;
            boolean found = false;
            for (int i = 0; i < hospitalAdministrator.getPatientList().get(PatientLoginController.index).getAppointments().size(); i++) {
                if (hospitalAdministrator.getPatientList().get(PatientLoginController.index).getAppointments().get(i).getAppointmentId().equals(id)) {
                    found = true;
                    patientAppointmentIndex = i;
                    break;
                }
            }

            if (found) {


                for (int i = 0; i < hospitalAdministrator.getDoctorList().size(); i++) {
                    for(int j=0;j<hospitalAdministrator.getDoctorList().get(i).getDoctorAppointments().size();j++){
                        if (hospitalAdministrator.getDoctorList().get(i).getDoctorAppointments().get(j).getAppointmentId().equals(id)) {
                            doctorIndex=i;
                            doctorAppointmentIndex = j;
                            break;
                        }
                    }
                }
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
                hospitalAdministrator.getPatientList().get(PatientLoginController.index).setBill(bill);
                    hospitalAdministrator.getPatientList().get(PatientLoginController.index).getAppointments().remove(patientAppointmentIndex);
                    hospitalAdministrator.getDoctorList().get(doctorIndex).getDoctorAppointments().remove(doctorAppointmentIndex);
                try {
                    Hospital_Administrator.switcher='p';
                    hospitalAdministrator.switchScene("pAppointmentsManagementScene.fxml");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setHeaderText("THE RECORD IS FOUND");
                    alert.setContentText("APPOINTMENT IS CANCELED SUCCESSFULLY");
                    alert.showAndWait();

                } catch (Exception e) {
                    System.out.println(e);
                }

            } else {
                try {
                    Hospital_Administrator.switcher='p';
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
    void goBackOnAction(ActionEvent event) {
        try {
            Hospital_Administrator.switcher='p';
            hospitalAdministrator.switchScene("pAppointmentsManagementScene.fxml");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
