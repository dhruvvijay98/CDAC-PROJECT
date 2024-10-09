
package com.HealtAnalizer.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="gst_license")
public class GSTLicense {

    @Id
    private  String gst_no;
    private String state;

    public GSTLicense(String gst_no, String state) {
        this.gst_no = gst_no;
        this.state = state;
    }

    

    public GSTLicense() {
    }

    public String getGst_no() {
        return gst_no;
    }

    public void setGst_no(String gst_no) {
        this.gst_no = gst_no;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    
    
}
