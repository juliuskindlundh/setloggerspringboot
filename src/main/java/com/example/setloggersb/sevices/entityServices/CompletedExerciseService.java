package com.example.setloggersb.sevices.entityServices;

import com.example.setloggersb.dtos.CompletedExerciseDTO;
import com.example.setloggersb.dtos.DataDTO;
import com.example.setloggersb.entities.BufferedCompletedExercise;
import com.example.setloggersb.entities.CompletedExercise;
import com.example.setloggersb.entities.Data;
import com.example.setloggersb.exceptionHandeling.Exceptions;
import com.example.setloggersb.repositorys.CompletedExerciseRepository;
import com.example.setloggersb.repositorys.DataRepository;
import com.example.setloggersb.repositorys.ExerciseRepository;
import com.example.setloggersb.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompletedExerciseService{

    CompletedExerciseRepository completedExerciseRepository;
    DataService dataService;
    ExerciseRepository exerciseRepository;
    DataRepository dataRepository;
    @Autowired
    public CompletedExerciseService(CompletedExerciseRepository completedExerciseRepository, DataService dataService, ExerciseRepository exerciseRepository,DataRepository dataRepository) {
        this.completedExerciseRepository = completedExerciseRepository;
        this.dataService = dataService;
        this.exerciseRepository = exerciseRepository;
        this.dataRepository = dataRepository;
    }

    public CompletedExercise create(BufferedCompletedExercise bce) throws Exception{
        CompletedExercise completedExercise = new CompletedExercise();
        completedExercise.setExercise(bce.getExercise());
        bce.getDataValues().forEach(v->{
            DataDTO dataDTO = new DataDTO(null,v);
            try {
                dataDTO = dataService.create(dataDTO);
                completedExercise.getDataIds().add(dataDTO.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
            List<CompletedExercise> completedExerciseList = completedExerciseRepository.findAllByExercise(completedExercise.getExercise()).stream().filter(e->{
                if(e.getDataIds().size() != completedExercise.getDataIds().size()){
                    return false;
                }
                for(int i = 0 ; i < e.getDataIds().size();i++){
                    if(e.getDataIds().get(i) != completedExercise.getDataIds().get(i)) {
                        return false;
                    }
                }
                return true;
            }).collect(Collectors.toList());
            System.out.println(completedExerciseList);
            if(completedExerciseList.size() == 0){
                return  completedExerciseRepository.save(completedExercise);
            }
            else{
                return completedExerciseList.get(0);
            }
    }

    public CompletedExerciseDTO getById(Long id) throws Exception {
        Optional<CompletedExercise> completedExerciseOptional = completedExerciseRepository.findById(id);
        if(completedExerciseOptional.isPresent()){
            return Converter.toDTO(completedExerciseOptional.get());
        }
        throw new Exceptions.EntityNotFoundException("CompletedExerciseId: "+id);
    }

    public List<CompletedExerciseDTO> getAll() {
        return null;
    }

    public CompletedExerciseDTO update(CompletedExerciseDTO completedExerciseDTO) throws Exception {
        return null;
    }

    public void deleteById(Long id) throws Exception {

    }


}
