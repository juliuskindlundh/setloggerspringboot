package com.example.setloggersb.sevices;

import com.example.setloggersb.dtos.InputFieldDTO;
import com.example.setloggersb.entities.InputField;
import com.example.setloggersb.repositorys.InputFieldRepository;
import com.example.setloggersb.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InputFieldService {

    InputFieldRepository inputFieldRepository;
    @Autowired
    public InputFieldService(InputFieldRepository inputFieldRepository){
        this.inputFieldRepository = inputFieldRepository;
    }
    public List<InputFieldDTO> getAll(){
        return inputFieldRepository.findAll().stream().map(Converter::toDTO).collect(Collectors.toList());
    }
}
