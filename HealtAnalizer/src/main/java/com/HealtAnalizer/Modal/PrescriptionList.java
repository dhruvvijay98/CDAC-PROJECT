/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.HealtAnalizer.Modal;

import com.HealtAnalizer.Entity.Medicine;

import com.HealtAnalizer.Entity.Medicine;
import com.HealtAnalizer.Entity.Prescriptions;

import java.util.List;

public class PrescriptionList {

    private int prescription_id;
  private Prescriptions prescription;
    
    private List<Medicine> medicines;

    public int getPrescription_id() {
        return prescription_id;
    }

    public void setPrescription_id(int prescription_id) {
        this.prescription_id = prescription_id;
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }

    public Prescriptions getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescriptions prescription) {
        this.prescription = prescription;
    }
    
    
    
    
    

     
     

}
