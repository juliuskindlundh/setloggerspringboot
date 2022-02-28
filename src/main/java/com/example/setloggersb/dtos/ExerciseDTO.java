package com.example.setloggersb.dtos;

import lombok.Value;

import java.util.List;

@Value
public class ExerciseDTO {

    @Value
    public static class Create{
        String name;
        List<String> inputFields;
    }

    @Value
    public static class Response{
        Long id;
        String name;
        List<String> inputFields;
    }
}
