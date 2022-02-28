package com.example.setloggersb.repositorys;

import com.example.setloggersb.entities.BufferedCompletedExercise;
import com.example.setloggersb.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BufferedCompletedExerciseRepository extends JpaRepository<BufferedCompletedExercise,Long> {
    List<BufferedCompletedExercise> findAllByUser(User user);
}
