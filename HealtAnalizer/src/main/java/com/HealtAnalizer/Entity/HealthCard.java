package com.HealtAnalizer.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "HealthCard")
public class HealthCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int healthCardId;
    private int patientId;
    private String bloodGroup;
    private double height;
    private double weight;
    private String previousDisease;
    private String previousPrescription;

    public HealthCard(int healthCardId, int patientId, String bloodGroup, double height, double weight, String previousDisease, String previousPrescription) {
        this.healthCardId = healthCardId;
        this.patientId = patientId;
        this.bloodGroup = bloodGroup;
        this.height = height;
        this.weight = weight;
        this.previousDisease = previousDisease;
        this.previousPrescription = previousPrescription;
    }

    public HealthCard() {
    }

    public int getHealthCardId() {
        return healthCardId;
    }

    public void setHealthCardId(int healthCardId) {
        this.healthCardId = healthCardId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getPreviousDisease() {
        return previousDisease;
    }

    public void setPreviousDisease(String previousDisease) {
        this.previousDisease = previousDisease;
    }

    public String getPreviousPrescription() {
        return previousPrescription;
    }

    public void setPreviousPrescription(String previousPrescription) {
        this.previousPrescription = previousPrescription;
    }

    
    
    

}
