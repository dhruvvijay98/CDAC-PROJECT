package com.HealtAnalizer.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name="doctor_detail")
public class Doctor {

    @Id
    private int doctorId;
    private String licence_no;
    private String hospitalName;
    private String qualification;

    public Doctor() {
    }

    public Doctor(int doctorId, String licence_no, String hospitalName, String qualification) {
        this.doctorId = doctorId;
        this.licence_no = licence_no;
        this.hospitalName = hospitalName;
        this.qualification = qualification;
    }


    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    

 
    public String getLicence_no() {
        return licence_no;
    }

    public void setLicence_no(String licence_no) {
        this.licence_no = licence_no;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

}
