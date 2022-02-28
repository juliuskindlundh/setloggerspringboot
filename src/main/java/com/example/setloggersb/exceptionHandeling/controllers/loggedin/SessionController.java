package com.example.setloggersb.exceptionHandeling.controllers.loggedin;

import com.example.setloggersb.security.UserDetailsImp;
import com.example.setloggersb.sevices.entityServices.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/i/session")
public class SessionController {

    SessionService sessionService;

    @Autowired
    public SessionController(SessionService sessionService){
        this.sessionService = sessionService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@AuthenticationPrincipal UserDetailsImp userDetailsImp) throws Exception {
        return ResponseEntity.ok().body(sessionService.generateSessionForUser(userDetailsImp.getId()));
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) throws Exception {
        return ResponseEntity.ok().body(sessionService.getById(id));
    }

    @GetMapping("/findall")
    public ResponseEntity<?> getall(){
        return ResponseEntity.ok().body(sessionService.getAll());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) throws Exception {
        sessionService.deleteById(id);
        return null;
    }

    @GetMapping("/findallbyuserid/")
    public ResponseEntity<?> findAllByUserId(@AuthenticationPrincipal UserDetailsImp userDetailsImp){
        return ResponseEntity.ok().body(sessionService.findAllByUserId(userDetailsImp));
    }

    @PutMapping("/compileintosessions")
    public ResponseEntity<?> compileIntoSessions(@AuthenticationPrincipal UserDetailsImp userDetailsImp) throws Exception {
        return ResponseEntity.ok().body(sessionService.generateSessionForUser(userDetailsImp.getId()));
    }
}
