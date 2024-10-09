
package com.HealtAnalizer.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="doctor_licence")
public class DoctorLicense {

    @Id
    private  String licenceId;
    private String university;

    public DoctorLicense(String licenceId, String university) {
        this.licenceId = licenceId;
        this.university = university;
    }

    public DoctorLicense() {
    }

    public String getLicenceId() {
        return licenceId;
    }

    public void setLicenceId(String licenceId) {
        this.licenceId = licenceId;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }
    
    
}
