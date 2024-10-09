package com.HealtAnalizer.Repository;

import com.HealtAnalizer.Entity.HealthCard;
import com.HealtAnalizer.Entity.HealthHistory;
import com.HealtAnalizer.Entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HealthHistoryRepo  extends JpaRepository<HealthHistory,Integer>{
    
    @Query("SELECT h FROM HealthHistory h WHERE h.healthCardId = :healthCardId")
    List<HealthHistory> findByHealthCardId(@Param("healthCardId") int healthCardId);
    
}
