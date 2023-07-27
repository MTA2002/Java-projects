package HMS_Classes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Patient extends Person{
     private String patientID;
     private boolean checkInStatus;
     private  double bill;
     private String checkInValue;
     private ObservableList<Appointment> patientAppointments= FXCollections.observableArrayList();

     private ObservableList<Prescription>prescriptions=FXCollections.observableArrayList();
     public Patient(){

     }

    public Patient(String name, int age, String phone,String gender ,String password,String patientID,boolean checkInStatus) {
        super(name, age, phone, gender,password);
        this.patientID = patientID;
        this.checkInStatus=checkInStatus;
    }

    public String getPatientID() {
         return patientID;
    }

    public void setPatientID(String patientID) {
         this.patientID = patientID;
    }

    public boolean isCheckInStatus() {
         return checkInStatus;
    }

    public void setCheckInStatus(boolean checkInStatus) {
         this.checkInStatus = checkInStatus;
    }

    public double getBill() {
        return bill;
    }

    public void setBill(double bill) {
        this.bill = bill;
    }

    public String getCheckInValue() {
         return checkInValue;
    }

    public void setCheckInValue(String checkInValue) {
        this.checkInValue = checkInValue;
    }

    public ObservableList<Appointment> getAppointments() {
        return patientAppointments;
    }

    public void setAppointments(ObservableList<Appointment> appointments) {
        this.patientAppointments = appointments;
    }

    public ObservableList<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(ObservableList<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientID='" + patientID + '\'' +
                ", checkInStatus=" + checkInStatus +
                ", bill=" + bill +
                ", checkInValue='" + checkInValue + '\'' +
                ", patientAppointments=" + patientAppointments +
                ", prescriptions=" + prescriptions +
                '}';
    }
}
