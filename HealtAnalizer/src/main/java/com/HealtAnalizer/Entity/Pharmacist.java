package com.HealtAnalizer.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="pharmacist_detail")
public class Pharmacist {
    
    @Id
    private int pharmacisId;
    
    private String qualification;
    private String gst_no;

    public Pharmacist(int pharmacisId, String qualification, String gst_no) {
        this.pharmacisId = pharmacisId;
        this.qualification = qualification;
        this.gst_no = gst_no;
    }

    public Pharmacist() {
    }
    
    

    public int getPharmacisId() {
        return pharmacisId;
    }

    public void setPharmacisId(int pharmacisId) {
        this.pharmacisId = pharmacisId;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getGst_no() {
        return gst_no;
    }

    public void setGst_no(String gst_no) {
        this.gst_no = gst_no;
    }

    

   
}
