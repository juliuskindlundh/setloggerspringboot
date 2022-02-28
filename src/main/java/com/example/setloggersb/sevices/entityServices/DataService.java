package com.example.setloggersb.sevices.entityServices;

import com.example.setloggersb.dtos.DataDTO;
import com.example.setloggersb.entities.Data;
import com.example.setloggersb.enums.EInputType;
import com.example.setloggersb.exceptionHandeling.Exceptions;
import com.example.setloggersb.repositorys.DataRepository;
import com.example.setloggersb.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DataService implements BasicCRUD<DataDTO,Long>{

    DataRepository dataRepository;

    @Autowired
    public DataService(DataRepository dataRepository){
        this.dataRepository = dataRepository;
    }

    @Override
    public DataDTO create(DataDTO dataDTO) throws Exception {
            Data data = new Data();
            data.setValue(dataDTO.getValue());
            try{
                data = dataRepository.save(data);
                return Converter.toDTO(data);
            }
            catch (DataIntegrityViolationException e){
                Optional<Data> optionalData = dataRepository.findDataByValue(data.getValue());
                if(optionalData.isPresent()){
                    return Converter.toDTO(optionalData.get());
                }
                else{
                    throw new Exception();
                }
            }
    }

    @Override
    public DataDTO getById(Long id) throws Exception {
        Optional<Data> optionalData = dataRepository.findById(id);
        if(optionalData.isPresent()){
            return Converter.toDTO(optionalData.get());
        }
        throw new Exceptions.EntityNotFoundException("dataId: "+id);
    }

    @Override
    public List<DataDTO> getAll() {
        return dataRepository.findAll().stream().map(Converter::toDTO).collect(Collectors.toList());
    }

    @Override
    public DataDTO update(DataDTO dataDTO) throws Exception {
        throw new Exceptions.NotImplemented("");
    }

    @Override
    public void deleteById(Long id) throws Exception {
        throw new Exceptions.NotImplemented("");
    }
}
