package com.example.setloggersb.exceptionHandeling.controllers.loggedin;

import com.example.setloggersb.dtos.BufferedCompletedExerciseDTO;
import com.example.setloggersb.security.UserDetailsImp;
import com.example.setloggersb.sevices.entityServices.BufferedCompletedExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/i/buffer")
public class BufferedCompletedExerciseController {

    BufferedCompletedExerciseService bufferedCompletedExerciseService;

    @Autowired
    public BufferedCompletedExerciseController(BufferedCompletedExerciseService bufferedCompletedExerciseService){
        this.bufferedCompletedExerciseService = bufferedCompletedExerciseService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody BufferedCompletedExerciseDTO dto, @AuthenticationPrincipal UserDetailsImp userDetailsImp) throws Exception {
        return ResponseEntity.ok().body(bufferedCompletedExerciseService.create(dto,userDetailsImp));
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) throws Exception {
        return ResponseEntity.ok().body(bufferedCompletedExerciseService.getById(id));
    }

    @GetMapping("/findallbyuserid")
    public ResponseEntity<?> findAllByUserId(@AuthenticationPrincipal UserDetailsImp userDetailsImp){
        return ResponseEntity.ok().body(bufferedCompletedExerciseService.findAllByUserId(userDetailsImp));
    }

    @GetMapping("/findall")
    public ResponseEntity<?> getall(){
        return ResponseEntity.ok().body(bufferedCompletedExerciseService.getAll());
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody BufferedCompletedExerciseDTO dto) throws Exception {
        return ResponseEntity.ok().body(bufferedCompletedExerciseService.update(dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) throws Exception {
        bufferedCompletedExerciseService.deleteById(id);
        return null;
    }
}
