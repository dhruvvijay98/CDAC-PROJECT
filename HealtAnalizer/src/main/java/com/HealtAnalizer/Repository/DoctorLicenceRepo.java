package com.HealtAnalizer.Repository;

import com.HealtAnalizer.Entity.Doctor;
import com.HealtAnalizer.Entity.DoctorLicense;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DoctorLicenceRepo  extends JpaRepository<DoctorLicense,String>{
    
    
}
