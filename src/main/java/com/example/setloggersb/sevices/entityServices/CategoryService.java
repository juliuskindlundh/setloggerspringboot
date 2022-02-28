package com.example.setloggersb.sevices.entityServices;


import com.example.setloggersb.dtos.CategoryDTO;
import com.example.setloggersb.dtos.ExerciseDTO;
import com.example.setloggersb.entities.Category;
import com.example.setloggersb.entities.Exercise;
import com.example.setloggersb.entities.user.User;
import com.example.setloggersb.enums.CategoryEnums;
import com.example.setloggersb.exceptionHandeling.Exceptions;
import com.example.setloggersb.repositorys.CategoryRepository;
import com.example.setloggersb.repositorys.ExerciseRepository;
import com.example.setloggersb.repositorys.UserRepository;
import com.example.setloggersb.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService{

    ExerciseRepository exerciseRepository;
    UserRepository userRepository;
    CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(ExerciseRepository exerciseRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.exerciseRepository = exerciseRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    public CategoryDTO.Response create(CategoryDTO.Create categoryDTO) throws Exception{
        if(categoryDTO.getName().equalsIgnoreCase("DEFAULT")){
            throw new Exceptions.BadRequest("Category name is not allowed");
        }
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setUser(userRepository.getById(categoryDTO.getUserId()));
        category.setParentId(categoryRepository.findAllByUserIdAndName(categoryDTO.getUserId(), CategoryEnums.DEFAULT.name()).get().getId());
        categoryRepository.save(category);
        return Converter.toDTO(category);
    }

    public CategoryDTO.Response getById(Long id) {
        if(categoryRepository.findById(id).isEmpty()){
            throw new Exceptions.EntityNotFoundException("categoryId: "+id);
        }
       return Converter.toDTO(categoryRepository.getById(id));
    }

    public List<CategoryDTO.Response> getAll() {
        return categoryRepository.findAll().stream().map(Converter::toDTO).collect(Collectors.toList());
    }

    public CategoryDTO.Response update(CategoryDTO.Update categoryDTO) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryDTO.getId());
        if(categoryOptional.isPresent()){
            Category category = categoryOptional.get();
            if(categoryDTO.getExerciseDTOs() != null){
                ArrayList<Exercise> temp = new ArrayList<>();
                categoryDTO.getExerciseDTOs().forEach(dto->{
                    Exercise exercise = exerciseRepository.findById(dto.getId()).get();
                    if(!temp.contains(exercise)){
                        temp.add(exercise);
                    }
                });

                categoryRepository.findAllByUserId(category.getUser().getId()).forEach(c-> temp.forEach(e->{
                    if(c.getExercises().contains(e)){
                        c.getExercises().remove(e);
                        categoryRepository.save(c);
                    }
                }));

                category.setExercises(temp);
            }else{
                category.setExercises(new ArrayList<>());
            }
            category.setName(categoryDTO.getName());

            if(category.getId() == categoryDTO.getParentId()){
                throw new Exceptions.BadRequest("tried to make parent child of self");
            }
            Long parentId = categoryDTO.getParentId();
            for(int i = 0; i< 25;i++){
                if(parentId == null){
                    break;
                }
                Category temp = categoryRepository.findById(parentId).get();
                if(temp.getParentId() == categoryDTO.getId()){
                    throw new Exceptions.BadRequest("tried to make parent child of self");
                }
                parentId = temp.getParentId();
            }

            category.setParentId(categoryDTO.getParentId());
            categoryRepository.save(category);
            return Converter.toDTO(category);
        }
        else{
            throw new Exceptions.EntityNotFoundException("categoryId: "+categoryDTO.getId());
        }
    }

    public void deleteById(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isPresent()){
            Category category = optionalCategory.get();

            Category defaultCategory = categoryRepository.findAllByUserIdAndName(category.getUser().getId(),CategoryEnums.DEFAULT.name()).get();
            defaultCategory.getExercises().addAll(category.getExercises());
            categoryRepository.save(defaultCategory);
            categoryRepository.deleteById(id);
        }
        throw new Exceptions.EntityNotFoundException("categoryId: " + id);
    }

    public List<CategoryDTO.Response> findAllByUserId(Long id) {
        return categoryRepository.findAllByUserId(id).stream().map(Converter::toDTO).collect(Collectors.toList());
    }
}
