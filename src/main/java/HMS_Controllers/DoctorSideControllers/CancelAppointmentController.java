package HMS_Controllers.DoctorSideControllers;

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
            int patientIndex=0;
            boolean found = false;
            for (int i = 0; i < hospitalAdministrator.getDoctorList().get(DoctorLoginController.index).getDoctorAppointments().size(); i++) {
                if (hospitalAdministrator.getDoctorList().get(DoctorLoginController.index).getDoctorAppointments().get(i).getAppointmentId().equals(id)) {
                    found = true;
                    doctorAppointmentIndex = i;
                    break;
                }
            }

            if (found) {
                for (int i = 0; i < hospitalAdministrator.getPatientList().size(); i++) {
                    for(int j=0;j<hospitalAdministrator.getPatientList().get(i).getAppointments().size();j++){
                        if (hospitalAdministrator.getPatientList().get(i).getAppointments().get(j).getAppointmentId().equals(id)) {
                            patientIndex=i;
                            patientAppointmentIndex = j;
                            break;
                        }
                    }

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
                    System.out.println(patientIndex+" "+patientAppointmentIndex);
                System.out.println(  hospitalAdministrator.getPatientList().get(patientIndex).getAppointments().size());
                    hospitalAdministrator.getPatientList().get(patientIndex).setBill(bill);
                    hospitalAdministrator.getPatientList().get(patientIndex).getAppointments().remove(patientAppointmentIndex);
                    hospitalAdministrator.getDoctorList().get(DoctorLoginController.index).getDoctorAppointments().remove(doctorAppointmentIndex);
                try {
                    Hospital_Administrator.switcher='d';
                    hospitalAdministrator.switchScene("dAppointmentsManagementScene.fxml");
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
    void goBackOnAction(ActionEvent event) {
        try {
            Hospital_Administrator.switcher='d';
            hospitalAdministrator.switchScene("dAppointmentsManagementScene.fxml");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
