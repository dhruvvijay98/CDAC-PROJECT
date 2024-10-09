package com.HealtAnalizer.Service;

import com.HealtAnalizer.Entity.Doctor;
import com.HealtAnalizer.Entity.DoctorLicense;
import com.HealtAnalizer.Entity.GSTLicense;
import com.HealtAnalizer.Entity.HealthCard;
import com.HealtAnalizer.Entity.HealthHistory;
import com.HealtAnalizer.Entity.Pharmacist;
import com.HealtAnalizer.Entity.Prescriptions;
import com.HealtAnalizer.Entity.User;
import com.HealtAnalizer.Enums.ResponseCode;
import com.HealtAnalizer.Modal.UserRoleUpgrade;
import com.HealtAnalizer.Repository.DoctorLicenceRepo;
import com.HealtAnalizer.Repository.DoctorRepo;
import com.HealtAnalizer.Repository.GSTLicenceRepo;
import com.HealtAnalizer.Repository.HealthCardRepo;
import com.HealtAnalizer.Repository.HealthHistoryRepo;
import com.HealtAnalizer.Repository.PharmasistRepo;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.HealtAnalizer.Repository.PrescriptionRepo;
import com.HealtAnalizer.Repository.UserRepo;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private HealthCardRepo healthCardRepo;

    @Autowired
    private PrescriptionRepo prescriptionRepo;

    @Autowired
    private HealthHistoryRepo healthHistoryRepo;

    @Autowired
    private DoctorRepo doctorRepo;

    @Autowired
    private PharmasistRepo pharmasistRepo;

    @Autowired
    private DoctorLicenceRepo doctorLicenceRepo;

    @Autowired
    private GSTLicenceRepo GSTLicenceRepo;

    public ResponseCode registerUser(User user) {
        try {
            // Hash the password before saving
            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            user.setPassword(hashedPassword);
            user.setRole("PATIENT");
            user.setHealthCardStatus("NOT GENERATED");
            userRepo.save(user);
            return ResponseCode.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseCode.FAILED;
        }
    }

    public ResponseCode change_user_role(UserRoleUpgrade userRoleUpdateRequest) {
        try {
            Optional<User> opt = userRepo.findById(userRoleUpdateRequest.getUserId());
           
            
           
            


            if ((opt.isPresent())) {
                User user = opt.get();
                user.setRole(userRoleUpdateRequest.getRole());


                if (userRoleUpdateRequest.getRole().equalsIgnoreCase("DOCTOR")) {
                    Doctor doctor = new Doctor();
                    doctor.setDoctorId(user.getUserId());
                    doctor.setLicence_no(userRoleUpdateRequest.getLicence_no());
                    doctor.setQualification(userRoleUpdateRequest.getQualification());
                    doctor.setHospitalName(userRoleUpdateRequest.getHospitalName());
                      Optional<DoctorLicense> license =doctorLicenceRepo.findById(userRoleUpdateRequest.getLicence_no());
                      if(license.isPresent())
                      {
                          DoctorLicense l2=license.get();
                          System.out.println(l2.getLicenceId()+" "+l2.getUniversity());
                       userRepo.save(user);
                       doctorRepo.save(doctor);
                      }
                    
                } else {
                    Pharmacist pharmacist = new Pharmacist();
                    pharmacist.setPharmacisId(user.getUserId());
                    pharmacist.setGst_no(userRoleUpdateRequest.getGst_no());
                    pharmacist.setQualification(userRoleUpdateRequest.getQualification());
                    Optional<GSTLicense> gst_no=GSTLicenceRepo.findById(userRoleUpdateRequest.getGst_no());
                    
                      if(gst_no.isPresent())
                      {
                          
                       userRepo.save(user);
                       pharmasistRepo.save(pharmacist);
                      }
                    

                }
               

                return ResponseCode.SUCCESS;
            } else {
                return ResponseCode.NOT_FOUND;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseCode.FAILED;
        }

    }

    public ResponseCode generateHealtCard(HealthCard healthCard) {

        try {
            healthCardRepo.save(healthCard);
            return ResponseCode.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseCode.FAILED;
        }

    }

    public User getUserAuthentication(User user) {

        try {
            User checkUser = userRepo.findByEmailId(user.getEmailId());
            String password = checkUser.getPassword();
            boolean status = verifyPassword(user.getPassword(), checkUser.getPassword());
            if (status) {
                return checkUser;
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public boolean verifyPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }

    public ResponseCode getUserPayment(int prescriptionId) {
        Optional<Prescriptions> opt = prescriptionRepo.findById(prescriptionId);
        if (opt.isPresent()) {
            Prescriptions prescription = opt.get();
            prescription.setStatus("PAID");
            prescriptionRepo.save(prescription);
            return ResponseCode.SUCCESS;
        } else {
            return ResponseCode.NOT_FOUND;
        }

    }

    public List<HealthHistory> getUserHealthHistory(int healthCardId) {
        List<HealthHistory> lst = healthHistoryRepo.findByHealthCardId(healthCardId);
        return lst;
    }

    public int getHealthCardId(int userId) {

        HealthCard healthCard = healthCardRepo.findByPatientId(userId);
        if (healthCard != null) {
            return healthCard.getHealthCardId();
        } else {
            return 0;
        }

    }

    public Doctor doctor(int doctorId) {
        Optional<Doctor> opt = doctorRepo.findById(doctorId);
        if (opt.isPresent()) {
            Doctor doctor = opt.get();
            return doctor;
        } else {
            return null;
        }
    }

    public Pharmacist getPharmistDetail(int userId) {
        Optional<Pharmacist> opt = pharmasistRepo.findById(userId);
        if (opt.isPresent()) {
            Pharmacist pharmist = opt.get();
            return pharmist;
        } else {
            return null;
        }
    }

}
