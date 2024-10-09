package com.HealtAnalizer.Modal;



public class UserRoleUpgrade {

    private int userId;
    private String role;
    private String qualification;
    
    //doctor
    private String licence_no;
    private String hospitalName;
    
    //pharmasist
    private String gst_no;
    
    
    public UserRoleUpgrade() {
    }

    public UserRoleUpgrade(int userId, String role, String licence_no, String gst_no) {
        this.userId = userId;
        this.role = role;
        this.licence_no = licence_no;
        this.gst_no = gst_no;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLicence_no() {
        return licence_no;
    }

    public void setLicence_no(String licence_no) {
        this.licence_no = licence_no;
    }

    public String getGst_no() {
        return gst_no;
    }

    public void setGst_no(String gst_no) {
        this.gst_no = gst_no;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }
    
    
    
}
