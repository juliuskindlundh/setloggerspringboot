package com.example.setloggersb.dtos;

import lombok.Value;

import java.util.List;

@Value
public class BufferedCompletedExerciseDTO {
    Long id;
    Long exerciseId;
    List<String> dataValues;
    Long timeStamp;
    Long userId;

    @Value
    public static class Response{
        Long id;
        ExerciseDTO.Response exercise;
        List<String> dataValues;
        Long timeStamp;
        Long userId;
    }
}
