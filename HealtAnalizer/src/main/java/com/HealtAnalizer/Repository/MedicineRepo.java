package com.HealtAnalizer.Repository;

import com.HealtAnalizer.Entity.HealthCard;
import com.HealtAnalizer.Entity.HealthHistory;
import com.HealtAnalizer.Entity.Medicine;
import com.HealtAnalizer.Entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MedicineRepo  extends JpaRepository<Medicine,Integer>{
    
      @Query("SELECT m FROM Medicine m WHERE m.prescriptionId = :prescriptionId")
    List<Medicine> findMedicinesByPrescriptionId(int prescriptionId);
    
}
