package HMS_Classes;

import java.time.LocalDate;

public class Prescription {
    private String prescriptionID;
    private String doctorName;
    private String patientName;
    private LocalDate date;
    private String  medicineName;
    private int doseOfMedicine;
    private int duration;

    public Prescription(String prescriptionID, String doctorName, String patientName, LocalDate date, String medicineName, int doseOfMedicine, int duration) {
        this.prescriptionID = prescriptionID;
        this.doctorName = doctorName;
        this.patientName = patientName;
        this.date = date;
        this.medicineName = medicineName;
        this.doseOfMedicine = doseOfMedicine;
        this.duration = duration;
    }

    public int getDoseOfMedicine() {
        return doseOfMedicine;
    }

    public void setDoseOfMedicine(int doseOfMedicine) {
        this.doseOfMedicine = doseOfMedicine;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medications) {
        this.medicineName = medicineName;
    }

    public String getPrescriptionID() {
        return prescriptionID;
    }

    public void setPrescriptionID(String prescriptionID) {
        this.prescriptionID = prescriptionID;
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "prescriptionID='" + prescriptionID + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", patientName='" + patientName + '\'' +
                ", date=" + date +
                ", medications=" + medicineName +
                ", doseOfMedicine=" + doseOfMedicine +
                ", duration=" + duration +
                '}';
    }
}
