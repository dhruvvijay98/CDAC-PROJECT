/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.HealtAnalizer.Modal;

import com.HealtAnalizer.Entity.HealthHistory;
import com.HealtAnalizer.Entity.Medicine;
import java.util.List;

public class HealthHistoryRequest {
    
    private HealthHistory healthHistory;
    private List<Medicine> medicine;

    public HealthHistory getHealthHistory() {
        return healthHistory;
    }

    public void setHealthHistory(HealthHistory healthHistory) {
        this.healthHistory = healthHistory;
    }

    public List<Medicine> getMedicine() {
        return medicine;
    }

    public void setMedicine(List<Medicine> medicine) {
        this.medicine = medicine;
    }
}


