package com.HealtAnalizer.Controller;

import com.HealtAnalizer.Entity.Doctor;
import com.HealtAnalizer.Entity.HealthCard;
import com.HealtAnalizer.Entity.HealthHistory;
import com.HealtAnalizer.Entity.Pharmacist;
import com.HealtAnalizer.Entity.User;
import com.HealtAnalizer.Enums.ResponseCode;
import com.HealtAnalizer.Modal.UserRoleUpgrade;
import com.HealtAnalizer.Service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        System.out.println("Registration");
        ResponseCode status = userService.registerUser(user);
        if (status == ResponseCode.SUCCESS) {
            return new ResponseEntity<>(status.name(), HttpStatus.OK);

        } else {
            return new ResponseEntity<>(status.name(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/change_role")
    public ResponseEntity<String> changeUserRole(@RequestBody UserRoleUpgrade user) {

        ResponseCode status = userService.change_user_role(user);
        if (status == ResponseCode.SUCCESS) {
            return new ResponseEntity<>(status.name(), HttpStatus.OK);

        } else if (status == ResponseCode.NOT_FOUND) {
            System.out.println("Account not found or licence invalid");
            return new ResponseEntity<>("Account not found or licence invalid", HttpStatus.OK);
        } else {
            System.out.println(status.name());
            return new ResponseEntity<>(status.name(), HttpStatus.BAD_REQUEST);
        }
    }
   
    @PostMapping("/generateHealthCard")
    public ResponseEntity<String> generateHealthCard(@RequestBody HealthCard healthCard) {

        ResponseCode status = userService.generateHealtCard(healthCard);
        if (status == ResponseCode.SUCCESS) {
            return new ResponseEntity<>(status.name(), HttpStatus.OK);

        } else if (status == ResponseCode.NOT_FOUND) {
            return new ResponseEntity<>("Account not found", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(status.name(), HttpStatus.BAD_REQUEST);
        }
    }
    
   @PostMapping("/login")
    public ResponseEntity<?> getUserLogin(@RequestBody User user) {
        User authenticatedUser = userService.getUserAuthentication(user);
        if (authenticatedUser != null) {
            return ResponseEntity.ok(authenticatedUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
        }
    }
    
    @PostMapping("/payment")
    public ResponseEntity<String> getMedicinePayment(@RequestParam("prescriptionId")  int prescriptionId) {
        ResponseCode status=userService.getUserPayment(prescriptionId);
        if (status == ResponseCode.SUCCESS) {
            return new ResponseEntity<>(status.name(), HttpStatus.OK);

        } else {
            return new ResponseEntity<>(status.name(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/userHealthHistory")
    public ResponseEntity<?> getUserHealthHistory(@RequestParam("healthCardId")  int healthCardId)
    {
        List<HealthHistory> history=userService.getUserHealthHistory(healthCardId);
        if(history!=null)
        {
            return ResponseEntity.ok(history);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No health History found");
        }
    }
    
     @GetMapping("/getHealthCardId")
    public ResponseEntity<?> getUserHealthCaedId(@RequestParam("userId")  int userId)
    {
        int healthId=userService.getHealthCardId(userId);
        if(healthId!=0)
        {
            return ResponseEntity.ok(healthId);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Health Card not generated");
        }
    }
    
     @GetMapping("/getDoctorDetail")
    public Doctor getDoctorDetails(@RequestParam("userId")  int userId)
    {
       Doctor doctor=userService.doctor(userId);
       return doctor;
    }
    
     @GetMapping("/getPharmistDetails")
    public Pharmacist getPharmistDetails(@RequestParam("userId")  int userId)
    {
       Pharmacist pharmist=userService.getPharmistDetail(userId);
       return pharmist;
    }
    
      

}
