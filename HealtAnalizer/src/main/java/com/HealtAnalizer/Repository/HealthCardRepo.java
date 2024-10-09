package com.HealtAnalizer.Repository;

import com.HealtAnalizer.Entity.HealthCard;
import com.HealtAnalizer.Entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HealthCardRepo  extends JpaRepository<HealthCard,Integer>{
     
     @Query("SELECT h FROM HealthCard h WHERE h.patientId = :patientId")
     HealthCard findByPatientId(@Param("patientId") int patientId);
    
}
