package com.HealtAnalizer.Service;

import com.HealtAnalizer.Entity.*;
import com.HealtAnalizer.Entity.User;
import com.HealtAnalizer.Enums.ResponseCode;
import com.HealtAnalizer.Modal.PrescriptionList;
import com.HealtAnalizer.Repository.DoctorRepo;
import com.HealtAnalizer.Repository.HealthCardRepo;
import com.HealtAnalizer.Repository.HealthHistoryRepo;
import com.HealtAnalizer.Repository.MedicineRepo;
import com.HealtAnalizer.Repository.PharmasistRepo;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.HealtAnalizer.Repository.PrescriptionRepo;
import com.HealtAnalizer.Repository.UserRepo;
import java.util.ArrayList;
import java.util.List;

@Service
public class HealthService {

    @Autowired
    private HealthCardRepo healthCardRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private HealthHistoryRepo healthHistoryRepo;

    @Autowired
    private PrescriptionRepo prescriptionRepo;

    @Autowired
    private MedicineRepo medicineRepo;
    
     @Autowired
    private DoctorRepo doctorRepo;
     
     @Autowired
     private PharmasistRepo pharmistRepo;


    public ResponseCode generateHealthCard(HealthCard healthCard) {
        try {
            Optional<User> opt = userRepo.findById(healthCard.getPatientId());
            User user = opt.get();
            user.setHealthCardStatus("GENERATED");
            userRepo.save(user);
            healthCardRepo.save(healthCard);
            return ResponseCode.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseCode.FAILED;
        }
    }

    public ResponseCode saveHealthHistory(HealthHistory healthHistory, List<Medicine> medicines) {
        try {
            List<Medicine> saveMedicineList = new ArrayList<>();
            for (Medicine medicine : medicines) {
                Medicine saveMedicine = medicineRepo.save(medicine);
                saveMedicineList.add(medicine);
            }

            Prescriptions prescription = new Prescriptions();
            prescription.setHealthCardId(healthHistory.getHealthCardId());
            Prescriptions savePrescriptionRequest = prescriptionRepo.save(prescription);

            for (Medicine medicine : saveMedicineList) {

                medicine.setPrescriptionId(savePrescriptionRequest.getPrescriptionId());
                medicineRepo.save(medicine);
            }

            healthHistory.setHealthCardId(healthHistory.getHealthCardId());
            healthHistory.setPrescriptionId(savePrescriptionRequest.getPrescriptionId());
            HealthHistory saveHealthHistoryRequest = healthHistoryRepo.save(healthHistory);

            savePrescriptionRequest.setStatus("PENDING");
            savePrescriptionRequest.setHealthHistoryId(saveHealthHistoryRequest.getHealthHistoryId());
            prescriptionRepo.save(savePrescriptionRequest);
            return ResponseCode.SUCCESS;

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseCode.FAILED;
        }
    }

    public List<PrescriptionList> getPrescriptionByHealthId(int healthId) {
        List<PrescriptionList> precriptionList = new ArrayList<>();
        List<Prescriptions> lst = prescriptionRepo.findPendingPrescriptionsByHealthCardId(healthId);

        for (Prescriptions prescrition : lst) {
            List<Medicine> medicines = medicineRepo.findMedicinesByPrescriptionId(prescrition.getPrescriptionId());
            PrescriptionList precriptionRequest = new PrescriptionList();
            precriptionRequest.setPrescription_id(prescrition.getPrescriptionId());
            precriptionRequest.setMedicines(medicines);
            precriptionRequest.setPrescription(prescrition);
            precriptionList.add(precriptionRequest);

        }
        return precriptionList;

    }

    public List<PrescriptionList> healthHistoryIdhealthHistoryId(int healthHistoryId) {
        List<PrescriptionList> precriptionList = new ArrayList<>();
        List<Prescriptions> lst = prescriptionRepo.findPendingPrescriptionsByHealthHistoryId(healthHistoryId);

        for (Prescriptions prescrition : lst) {
            List<Medicine> medicines = medicineRepo.findMedicinesByPrescriptionId(prescrition.getPrescriptionId());
            PrescriptionList precriptionRequest = new PrescriptionList();
            precriptionRequest.setPrescription_id(prescrition.getPrescriptionId());
            precriptionRequest.setMedicines(medicines);
            precriptionRequest.setPrescription(prescrition);
            precriptionList.add(precriptionRequest);

        }
        return precriptionList;

    }
    
    public ResponseCode updateMedicine(List<Medicine> medicine)
    {
    try{
        System.out.println(medicine.size());
        for(Medicine med:medicine)
        {
          medicineRepo.save(med);
        }
        return ResponseCode.SUCCESS;
    }catch(Exception e)
    {
     e.printStackTrace();
     return ResponseCode.FAILED;
    }
        
    }
    
    

}
