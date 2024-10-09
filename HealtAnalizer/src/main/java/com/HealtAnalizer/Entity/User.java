package com.HealtAnalizer.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.Date;

@Entity
@Table(name = "user", uniqueConstraints = {
    @UniqueConstraint(columnNames = "emailId"),
    @UniqueConstraint(columnNames = "addharId")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
    private int userId;

    @Column(nullable = false, unique = true)
    private String emailId;
    private String password;
    private String name;
    @Column(nullable = false, unique = true)
    private long addharId;
    private String role;
    private Date dob;
    private String healthCardStatus;

    public User() {
    }

    public User(int userId, String emailId, String password, int addharId, String role) {
        this.userId = userId;
        this.emailId = emailId;
        this.password = password;
        this.addharId = addharId;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    
    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getAddharId() {
        return addharId;
    }

    public void setAddharId(long addharId) {
        this.addharId = addharId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getHealthCardStatus() {
        return healthCardStatus;
    }

    public void setHealthCardStatus(String healthCardStatus) {
        this.healthCardStatus = healthCardStatus;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
    
}
