package com.example.setloggersb.dtos;

import lombok.Data;
import lombok.Value;

import java.util.List;

@Value
@lombok.Data
public class CompletedExerciseDTO {
    Long id;
    ExerciseDTO.Response exercise;
    List<Long> dataIds;

    @Value
    @Data
    public static class Response{
        Long id;
        ExerciseDTO.Response exercise;
        List<DataDTO> dataDTOs;
    }
}
