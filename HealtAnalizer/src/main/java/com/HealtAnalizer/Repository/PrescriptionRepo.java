package com.HealtAnalizer.Repository;

import com.HealtAnalizer.Entity.Prescriptions;
import com.HealtAnalizer.Entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PrescriptionRepo  extends JpaRepository<Prescriptions,Integer>{
  
    @Query("SELECT p FROM Prescriptions p WHERE p.healthCardId = :healthCardId AND p.status = 'Pending'")
    List<Prescriptions> findPendingPrescriptionsByHealthCardId(int healthCardId);
    
     @Query("SELECT p FROM Prescriptions p WHERE p.healthHistoryId = :healthHistoryId")
    List<Prescriptions> findPendingPrescriptionsByHealthHistoryId(int healthHistoryId);
    



}
