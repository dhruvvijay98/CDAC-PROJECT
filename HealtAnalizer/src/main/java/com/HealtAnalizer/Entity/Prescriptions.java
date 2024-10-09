package com.HealtAnalizer.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.List;
import java.util.Set;


   
@Entity
@Table(name = "Prescriptions1aug")
public class Prescriptions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int prescriptionId;

    private String status;
    private int healthHistoryId;
    private int healthCardId;
    private Date date=new Date();

    public Prescriptions() {
    }

    

   
  
    
    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

  
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

    

   
}

