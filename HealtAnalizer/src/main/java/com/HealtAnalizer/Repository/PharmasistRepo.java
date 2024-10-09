package com.HealtAnalizer.Repository;

import com.HealtAnalizer.Entity.Doctor;
import com.HealtAnalizer.Entity.Pharmacist;
import com.HealtAnalizer.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PharmasistRepo  extends JpaRepository<Pharmacist,Integer>{
    
    
}
