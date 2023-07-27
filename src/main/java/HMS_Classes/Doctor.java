package HMS_Classes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

public class Doctor extends Staff{

    private String speciality;
    private ObservableList<Appointment> doctorAppointments= FXCollections.observableArrayList();

    public Doctor(String name, int age, String phone,String gender,String password, String staffId,double salary,String speciality) {
        super(name, age, phone, gender,password,staffId, salary);
        this.speciality = speciality;
    }

    public String getSpeciality() {

        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public ObservableList<Appointment> getDoctorAppointments() {
        return doctorAppointments;
    }

    public void setDoctorAppointments(ObservableList<Appointment> doctorAppointments) {
        this.doctorAppointments = doctorAppointments;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "speciality='" + speciality + '\'' +
                '}';
    }
}
