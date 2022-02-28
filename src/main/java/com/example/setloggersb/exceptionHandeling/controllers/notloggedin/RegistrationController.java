package com.example.setloggersb.exceptionHandeling.controllers.notloggedin;

import com.example.setloggersb.dtos.RegistrationDTO;
import com.example.setloggersb.sevices.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/registration")
public class RegistrationController {

    RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService){
        this.registrationService = registrationService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistrationDTO dto) throws Exception {
        return ResponseEntity.ok(registrationService.register(dto));
    }

}
