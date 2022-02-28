package com.example.setloggersb.sevices.entityServices;


import java.util.List;
import java.util.Optional;

public interface BasicCRUD <DTO,Long>{
    public DTO create(DTO dto) throws Exception;
    public DTO getById(Long id) throws Exception;
    public List<DTO> getAll();
    public DTO update(DTO dto) throws Exception;
    public void deleteById(Long id) throws Exception;
}
