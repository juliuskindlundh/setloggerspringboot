package com.example.setloggersb.util;

import com.example.setloggersb.dtos.*;
import com.example.setloggersb.entities.*;
import com.example.setloggersb.entities.user.User;
import com.example.setloggersb.repositorys.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class Converter {

    public static UserDTO toDTO(User user){
        return new UserDTO(user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRoles());
    }

    public static CategoryDTO.Response toDTO(Category category){
        return new CategoryDTO.Response(category.getId(),
                category.getParentId(),
                category.getName(),
                category.getExercises().stream().map(Converter::toDTO).collect(Collectors.toList()),
                category.getUser().getId());
    }

    public static DataDTO toDTO(Data data) {
        return new DataDTO(data.getId(),data.getValue());
    }

    public static ExerciseDTO.Response toDTO(Exercise exercise){
        return new ExerciseDTO.Response(exercise.getId(),
                exercise.getName(),
                exercise.getInputFields().stream().map(a->a.getName()).collect(Collectors.toList()));
    }

    public static BufferedCompletedExerciseDTO.Response toDTO(BufferedCompletedExercise bce) {
        return new BufferedCompletedExerciseDTO.Response(bce.getId(),Converter.toDTO(bce.getExercise()),bce.getDataValues(),bce.getTimeStamp(),bce.getUser().getId());
    }

    public static SessionDTO toDTO(Session session) {
        return new SessionDTO(session.getId(), session.getFistCompletedExercise(), session.getLastCompletedExercise(),session.getCompletedExercises(), session.getUser());
    }

    public static CompletedExerciseDTO toDTO(CompletedExercise completedExercise) {
        return new CompletedExerciseDTO(completedExercise.getId(), toDTO(completedExercise.getExercise()),completedExercise.getDataIds());
    }

    public static InputFieldDTO toDTO(InputField inputField) {
        return new InputFieldDTO(inputField.getId(),inputField.getName(),inputField.getDefinition());
    }
}
