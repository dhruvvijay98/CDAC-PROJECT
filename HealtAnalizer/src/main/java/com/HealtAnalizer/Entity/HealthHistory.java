package com.HealtAnalizer.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "HealthHistory")
public class HealthHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int healthHistoryId;
    private int healthCardId;
    private String description;
    private Date diagnosedDate;
    private String doctorName;
    private int prescriptionId;

    public int getHealthHistoryId() {
        return healthHistoryId;
    }

    public void setHealthHistoryId(int healthHistoryId) {
        this.healthHistoryId = healthHistoryId;
    }

    public int getHealthCardId() {
        return healthCardId;
    }

    public void setHealthCardId(int healthCardId) {
        this.healthCardId = healthCardId;
    }

   

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDiagnosedDate() {
        return diagnosedDate;
    }

    public void setDiagnosedDate(Date diagnosedDate) {
        this.diagnosedDate = diagnosedDate;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

   
}
