package com.example.setloggersb.repositorys;

import com.example.setloggersb.entities.Category;
import com.example.setloggersb.entities.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    List<Category> findAllByUserId(Long userId);
    Optional<Category> findAllByUserIdAndName(Long userId,String name);
}
