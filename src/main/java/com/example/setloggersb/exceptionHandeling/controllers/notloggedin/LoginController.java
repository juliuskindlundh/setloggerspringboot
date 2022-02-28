package com.example.setloggersb.exceptionHandeling.controllers.notloggedin;

import com.example.setloggersb.dtos.LoginDTO;
import com.example.setloggersb.sevices.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/login")
public class LoginController {

    LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService){
        this.loginService = loginService;
    }

    @PostMapping()
    public ResponseEntity<?> login(@RequestBody LoginDTO dto){
        System.out.println(dto);
        return ResponseEntity.ok(loginService.login(dto));
    }

}
