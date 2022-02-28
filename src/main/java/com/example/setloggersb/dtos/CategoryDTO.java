package com.example.setloggersb.dtos;
import lombok.Value;
import org.springframework.lang.Nullable;

import java.util.List;

public class CategoryDTO{

    @Value
    public static class Create{
        String name;
        Long userId;
    }

    @Value
    public static class Update{
        Long id;
        Long parentId;
        String name;
        @Nullable
        List<ExerciseDTO.Response> exerciseDTOs;
    }

    @Value
    public static class Response{
        Long id;
        Long parentId;
        String name;
        List<ExerciseDTO.Response> exerciseDTOs;
        Long userId;
    }
}
