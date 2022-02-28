package com.example.setloggersb.repositorys;

import com.example.setloggersb.entities.CompletedExercise;
import com.example.setloggersb.entities.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface CompletedExerciseRepository extends JpaRepository<CompletedExercise,Long> {
    List<CompletedExercise> findAllByExercise(Exercise exercise);
}
