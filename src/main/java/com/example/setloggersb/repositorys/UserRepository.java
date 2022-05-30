package com.example.setloggersb.repositorys;

import com.example.setloggersb.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmailOrUsername(String email, String username);
    Optional<User> findByEmail(String email);
    Optional<Object> findByUsername(String username);
}
