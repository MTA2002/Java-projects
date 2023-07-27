package HMS_Classes;

import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.util.Callback;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class Appointment {
    private String appointmentId;
    private LocalDate localDate;
    private LocalTime localTime;
    private Patient patient;
    private Doctor doctor;

    public Appointment(String appointmentId, LocalDate localDate, LocalTime localTime) {
        this.appointmentId = appointmentId;
        this.localDate = localDate;
        this.localTime = localTime;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public LocalDate getDate() {
        return localDate;
    }

    public void setDate(Date date) {
        this.localDate = localDate;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        return "appointment{" +
                "appointmentId='" + appointmentId + '\'' +
                ", date=" + localDate +
                ", localTime=" + localTime +
                ", patient=" + patient +
                ", doctor=" + doctor +
                '}';
    }
}
