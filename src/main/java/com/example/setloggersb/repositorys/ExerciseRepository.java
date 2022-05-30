package com.example.setloggersb.repositorys;

import com.example.setloggersb.dtos.ExerciseDTO;
import com.example.setloggersb.entities.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise,Long> {
}
