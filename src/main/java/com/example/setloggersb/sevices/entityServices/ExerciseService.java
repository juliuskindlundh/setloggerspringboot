package com.example.setloggersb.sevices.entityServices;

import com.example.setloggersb.dtos.CategoryDTO;
import com.example.setloggersb.dtos.ExerciseDTO;
import com.example.setloggersb.entities.Category;
import com.example.setloggersb.entities.Exercise;
import com.example.setloggersb.entities.InputField;
import com.example.setloggersb.enums.CategoryEnums;
import com.example.setloggersb.exceptionHandeling.Exceptions;
import com.example.setloggersb.repositorys.CategoryRepository;
import com.example.setloggersb.repositorys.ExerciseRepository;
import com.example.setloggersb.repositorys.InputFieldRepository;
import com.example.setloggersb.repositorys.UserRepository;
import com.example.setloggersb.security.UserDetailsImp;
import com.example.setloggersb.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExerciseService {

    InputFieldRepository inputFieldRepository;
    ExerciseRepository exerciseRepository;
    UserRepository userRepository;
    CategoryRepository categoryRepository;
    CategoryService categoryService;
    CompletedExerciseService completedExerciseService;

    @Autowired
    public ExerciseService(InputFieldRepository inputFieldRepository, ExerciseRepository exerciseRepository, UserRepository userRepository,CategoryRepository categoryRepository, CategoryService categoryService,CompletedExerciseService completedExerciseService) {
        this.inputFieldRepository = inputFieldRepository;
        this.exerciseRepository = exerciseRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
    }

    public ExerciseDTO.Response create(ExerciseDTO.Create exerciseDTO, UserDetailsImp userDetailsImp) throws Exception {
        nameIsUniqueForUser(exerciseDTO.getName(),userDetailsImp.getId());
        Exercise exercise = new Exercise();
        exercise.setName(exerciseDTO.getName());
        ArrayList<InputField> temp = new ArrayList<>();
        exerciseDTO.getInputFields().forEach(a->{
            Optional<InputField> optionalInputField = inputFieldRepository.findByName(a);
            optionalInputField.ifPresent(temp::add);
        });
        exercise.setInputFields(temp);
        exerciseRepository.save(exercise);

        Optional<Category> optionalCategory = categoryRepository.findAllByUserIdAndName(userDetailsImp.getId(), CategoryEnums.DEFAULT.name());
        if(optionalCategory.isPresent()){
            optionalCategory.get().getExercises().add(exercise);
            categoryRepository.save(optionalCategory.get());
        }
        else{
            CategoryDTO.Response categoryDTO = categoryService.create(new CategoryDTO.Create(CategoryEnums.DEFAULT.name(), userDetailsImp.getId()));
            Category category = categoryRepository.getById(categoryDTO.getId());
            category.getExercises().add(exercise);
            categoryRepository.save(category);
        }

        return Converter.toDTO(exercise);
    }

    private void nameIsUniqueForUser(String name, Long id) throws Exceptions.NameNotUnique {
        categoryRepository.findAllByUserId(id).forEach(c-> c.getExercises().forEach(e->{
            if(e.getName().equalsIgnoreCase(name)){
                throw new Exceptions.NameNotUnique("The provided name: "+name+" is already in use");
            }
        }));


    }

    public ExerciseDTO.Response getById(Long id) throws Exception {
        Optional<Exercise> optionalExercise = exerciseRepository.findById(id);
        if(optionalExercise.isPresent()){
            return Converter.toDTO(optionalExercise.get());
        }
        throw new Exceptions.EntityNotFoundException("exceptionId: "+id);
    }

    public List<ExerciseDTO.Response> getAll() {
        return exerciseRepository.findAll().stream().map(Converter::toDTO).collect(Collectors.toList());
    }

    public ExerciseDTO.Response update(ExerciseDTO.Response exerciseDTO,UserDetailsImp userDetailsImp) throws Exception {

        validateExerciseId(exerciseDTO.getId());

        Exercise exercise = exerciseRepository.getById(exerciseDTO.getId());
        exercise.setName(exerciseDTO.getName());

        if(exercise.inputFieldsEquals(exerciseDTO.getInputFields())){
            ArrayList<InputField> temp = new ArrayList<>();
            exerciseDTO.getInputFields().forEach(a->{
                Optional<InputField> optionalInputField = inputFieldRepository.findByName(a);
                optionalInputField.ifPresent(temp::add);
            });
            exercise.setInputFields(temp);
            exerciseRepository.save(exercise);
            return Converter.toDTO(exercise);
        }
        else{
            exercise.setName("-"+exerciseDTO.getName());
            Exercise newExercise = new Exercise();
            newExercise.setName(exerciseDTO.getName());
            ArrayList<InputField> temp = new ArrayList<>();
            exerciseDTO.getInputFields().forEach(a->{
                Optional<InputField> optionalInputField = inputFieldRepository.findByName(a);
                optionalInputField.ifPresent(temp::add);
            });
            newExercise.setInputFields(temp);
            exerciseRepository.save(newExercise);

            Category category = null;
            List<Category> list = categoryRepository.findAllByUserId(userDetailsImp.getId());
            for (Category c:list) {
                if(c.getExercises().contains(exercise)){
                    category = c;
                }
            }
            if(category != null){
                category.getExercises().remove(exercise);
                category.getExercises().add(newExercise);
            }
            categoryRepository.save(category);



            return Converter.toDTO(exercise);
        }


    }

    public void deleteById(Long id) throws Exception {
        throw new Exceptions.NotImplemented("");
    }

    private void validateExerciseId(Long id){
        if(exerciseRepository.findById(id).isEmpty()){
            throw new Exceptions.EntityNotFoundException("exerciseId: "+id);
        }
    }

}
