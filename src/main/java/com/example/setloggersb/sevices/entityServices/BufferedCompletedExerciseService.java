package com.example.setloggersb.sevices.entityServices;

import com.example.setloggersb.dtos.BufferedCompletedExerciseDTO;
import com.example.setloggersb.entities.BufferedCompletedExercise;
import com.example.setloggersb.entities.Exercise;
import com.example.setloggersb.entities.user.User;
import com.example.setloggersb.exceptionHandeling.Exceptions;
import com.example.setloggersb.repositorys.BufferedCompletedExerciseRepository;
import com.example.setloggersb.repositorys.ExerciseRepository;
import com.example.setloggersb.repositorys.UserRepository;
import com.example.setloggersb.security.UserDetailsImp;
import com.example.setloggersb.util.Converter;
import com.example.setloggersb.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BufferedCompletedExerciseService {
    ExerciseRepository exerciseRepository;
    UserRepository userRepository;
    DataService dataService;
    BufferedCompletedExerciseRepository bufferedCompletedExerciseRepository;

    @Autowired
    public BufferedCompletedExerciseService(ExerciseRepository exerciseRepository, UserRepository userRepository, DataService dataService,
                                            BufferedCompletedExerciseRepository bufferedCompletedExerciseRepository) {
        this.exerciseRepository = exerciseRepository;
        this.userRepository = userRepository;
        this.dataService = dataService;
        this.bufferedCompletedExerciseRepository = bufferedCompletedExerciseRepository;
    }

    public BufferedCompletedExerciseDTO.Response create(BufferedCompletedExerciseDTO bceDTO, UserDetailsImp  userDetailsImp) throws Exception {
        Validator.isReal(Exercise.class,bceDTO.getExerciseId());

        BufferedCompletedExercise bce = new BufferedCompletedExercise();
        bce.setExercise(exerciseRepository.getById(bceDTO.getExerciseId()));
        bce.setUser(userRepository.getById(userDetailsImp.getId()));
        bce.setTimeStamp(bceDTO.getTimeStamp() == null ? System.currentTimeMillis():bceDTO.getTimeStamp());
        bce.setDataValues(bceDTO.getDataValues());
        bufferedCompletedExerciseRepository.save(bce);
        return Converter.toDTO(bce);
    }

    public BufferedCompletedExerciseDTO.Response getById(Long id) throws Exception {
        Optional<BufferedCompletedExercise> bufferedCompletedExerciseOptional = bufferedCompletedExerciseRepository.findById(id);
        if(bufferedCompletedExerciseOptional.isPresent()){
            return Converter.toDTO(bufferedCompletedExerciseOptional.get());
        }
        throw new Exceptions.EntityNotFoundException("bufferedCompletedExerciseId: "+id);
    }

    public List<BufferedCompletedExerciseDTO.Response> getAll() {
        return bufferedCompletedExerciseRepository.findAll().stream().map(Converter::toDTO).collect(Collectors.toList());
    }

    public BufferedCompletedExerciseDTO.Response update(BufferedCompletedExerciseDTO bceDTO) throws Exception {
        Validator.isReal(Exercise.class,bceDTO.getExerciseId());
        Validator.isReal(User.class,bceDTO.getUserId());

        Optional<BufferedCompletedExercise> optionalBufferedCompletedExercise = bufferedCompletedExerciseRepository.findById(bceDTO.getId());
        if(optionalBufferedCompletedExercise.isPresent()){
            BufferedCompletedExercise bce = optionalBufferedCompletedExercise.get();
            bce.setExercise(exerciseRepository.getById(bceDTO.getExerciseId()));
            bce.setUser(userRepository.getById(bceDTO.getUserId()));
            bce.setTimeStamp(bceDTO.getTimeStamp() == null ? System.currentTimeMillis():bceDTO.getTimeStamp());
            bce.setDataValues(bceDTO.getDataValues());
            bufferedCompletedExerciseRepository.save(bce);
            return Converter.toDTO(bce);
        }
        throw new Exceptions.EntityNotFoundException("bufferedCompletedExerciseId: "+bceDTO.getId());
    }

    public void deleteById(Long id) throws Exception {
        throw new Exceptions.NotImplemented("");
    }

    public List<BufferedCompletedExerciseDTO.Response> findAllByUserId(UserDetailsImp userDetailsImp) {
        return bufferedCompletedExerciseRepository.findAllByUser(userRepository.getById(userDetailsImp.getId())).stream().map(Converter::toDTO).collect(Collectors.toList());
    }
}
