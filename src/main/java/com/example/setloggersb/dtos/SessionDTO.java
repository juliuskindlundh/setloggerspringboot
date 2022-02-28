package com.example.setloggersb.dtos;

import com.example.setloggersb.entities.CompletedExercise;
import com.example.setloggersb.entities.user.User;
import lombok.*;

import java.util.List;

@Value
public class SessionDTO {
    Long id;
    Long fistCompletedExercise;
    Long lastCompletedExercise;
    List<CompletedExercise> completedExercises;
    User user;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Response{
        Long id;
        Long fistCompletedExercise;
        Long lastCompletedExercise;
        List<CompletedExerciseDTO.Response> completedExercises;
    }
}
