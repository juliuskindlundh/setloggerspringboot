package com.example.setloggersb.repositorys;

import com.example.setloggersb.entities.InputField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InputFieldRepository extends JpaRepository<InputField,Long> {
    Optional<InputField> findByName(String name);
}
