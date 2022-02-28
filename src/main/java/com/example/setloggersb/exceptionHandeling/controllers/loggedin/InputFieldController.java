package com.example.setloggersb.exceptionHandeling.controllers.loggedin;

import com.example.setloggersb.sevices.InputFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/i/inputfields")
public class InputFieldController {

    InputFieldService inputFieldService;

    @Autowired
    public InputFieldController(InputFieldService inputFieldService){
        this.inputFieldService = inputFieldService;
    }

    @GetMapping("/findall")
    public ResponseEntity<?> getall(){
        return ResponseEntity.ok().body(inputFieldService.getAll());
    }

}
