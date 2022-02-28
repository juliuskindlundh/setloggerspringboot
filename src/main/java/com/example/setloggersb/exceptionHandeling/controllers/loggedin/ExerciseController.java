package com.example.setloggersb.exceptionHandeling.controllers.loggedin;
import com.example.setloggersb.dtos.ExerciseDTO;
import com.example.setloggersb.security.UserDetailsImp;
import com.example.setloggersb.sevices.entityServices.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/i/exercise")
public class ExerciseController {

    ExerciseService exerciseService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService){
        this.exerciseService = exerciseService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ExerciseDTO.Create exerciseDTO, @AuthenticationPrincipal UserDetailsImp userDetailsImp) throws Exception {
        return ResponseEntity.ok(exerciseService.create(exerciseDTO,userDetailsImp));
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(exerciseService.getById(id));
    }

    @GetMapping("/findall")
    public ResponseEntity<?> getall(){
        return ResponseEntity.ok().body(exerciseService.getAll());
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody ExerciseDTO.Response dto,@AuthenticationPrincipal UserDetailsImp userDetailsImp) throws Exception {
        return ResponseEntity.ok().body(exerciseService.update(dto,userDetailsImp));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) throws Exception {
        exerciseService.deleteById(id);
        return null;
    }
}
