package com.example.setloggersb.entities;

import com.example.setloggersb.entities.user.User;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private  Long fistCompletedExercise;
    private Long lastCompletedExercise;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<CompletedExercise> completedExercises = new ArrayList<>();
    @ManyToOne
    private User user;

    public void addCompletedExercise(CompletedExercise completedExercise,Long timeStamp) {
        if(timeStamp < fistCompletedExercise){
            this.fistCompletedExercise = timeStamp;
        }
        if (timeStamp > lastCompletedExercise){
            this.lastCompletedExercise = timeStamp;
        }
        completedExercises.add(completedExercise);
    }
}
