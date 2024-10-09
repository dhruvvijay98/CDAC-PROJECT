package com.HealtAnalizer.Controller;

import com.HealtAnalizer.Entity.HealthCard;
import com.HealtAnalizer.Entity.Medicine;

import com.HealtAnalizer.Enums.ResponseCode;
import com.HealtAnalizer.Modal.HealthHistoryRequest;
import com.HealtAnalizer.Modal.PrescriptionList;
import com.HealtAnalizer.Service.HealthService;
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
@RequestMapping("/healthcard")
public class HealthCardController {

    @Autowired
    private HealthService service;

    @PostMapping("/generate_health_card")
    public ResponseEntity<String> generateHealthCard(@RequestBody HealthCard healthCard) {

        ResponseCode status = service.generateHealthCard(healthCard);

        if (status == ResponseCode.SUCCESS) {
            return new ResponseEntity<>(status.name(), HttpStatus.OK);

        } else {
            return new ResponseEntity<>(status.name(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/save_health_history")
public ResponseEntity<String> saveHealthHistory(@RequestBody HealthHistoryRequest request) {
  
    ResponseCode status = service.saveHealthHistory(request.getHealthHistory(), request.getMedicine());
    if (status == ResponseCode.SUCCESS) {
        return new ResponseEntity<>(status.name(), HttpStatus.OK);
    } else {
        return new ResponseEntity<>(status.name(), HttpStatus.BAD_REQUEST);
    }
}

    
    @GetMapping("/getPrescriptionByHealthIdPharmasist")
    public List<PrescriptionList> getPrescriptionByHealthIdPharmasist(@RequestParam("health_card_id") int health_card_id) {
    
        List<PrescriptionList> lst=service.getPrescriptionByHealthId(health_card_id);
        return lst;
        
    
    }
    
    @GetMapping("/getPrescriptionByHealthHistoryIdDoctor")
    public List<PrescriptionList> getPrescriptionByHealthHistoryIdDoctor(@RequestParam("health_history_id") int health_history_id) {
    
        List<PrescriptionList> lst=service.healthHistoryIdhealthHistoryId(health_history_id);
        return lst;
        
    
    }
    
    @GetMapping("/getPrescriptionByHealthHistoryPatient")
    public List<PrescriptionList> getPrescriptionByHealthHistoryIdPatient(@RequestParam("health_history_id") int health_history_id) {
    
        List<PrescriptionList> lst=service.healthHistoryIdhealthHistoryId(health_history_id);
        return lst;
        
    
    }
    @PostMapping("/update_medicine")
    public ResponseEntity<String> updateMedicine(@RequestBody List<Medicine> medicine) {
    
        ResponseCode status=service.updateMedicine(medicine);
       if (status == ResponseCode.SUCCESS) {
        return new ResponseEntity<>(status.name(), HttpStatus.OK);
    } else {
        return new ResponseEntity<>(status.name(), HttpStatus.BAD_REQUEST);
    }
        
    
    }
    
    

}
