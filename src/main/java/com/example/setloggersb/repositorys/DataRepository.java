package com.example.setloggersb.repositorys;

import com.example.setloggersb.entities.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DataRepository extends JpaRepository<Data,Long> {
    Optional<Data> findDataByValue(String value);
}
