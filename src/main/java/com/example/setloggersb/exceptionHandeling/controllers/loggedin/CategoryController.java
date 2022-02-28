package com.example.setloggersb.exceptionHandeling.controllers.loggedin;

import com.example.setloggersb.dtos.CategoryDTO;
import com.example.setloggersb.sevices.entityServices.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/i/category")
public class CategoryController {

    CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CategoryDTO.Create dto) throws Exception {
        return ResponseEntity.ok().body(categoryService.create(dto));
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(categoryService.getById(id));
    }

    @GetMapping("/findall")
    public ResponseEntity<?> getall(){
        return ResponseEntity.ok().body(categoryService.getAll());
    }

    @GetMapping("findallbyuserid/{id}")
    public ResponseEntity<?> findAllByUserId(@PathVariable Long id){
        return ResponseEntity.ok().body(categoryService.findAllByUserId(id));
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody CategoryDTO.Update dto){
        return ResponseEntity.ok().body(categoryService.update(dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        categoryService.deleteById(id);
        return null;
    }
}
