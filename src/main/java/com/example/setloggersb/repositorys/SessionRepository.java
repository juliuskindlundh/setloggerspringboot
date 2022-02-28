package com.example.setloggersb.repositorys;

import com.example.setloggersb.entities.Session;
import com.example.setloggersb.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session,Long> {
    List<Session> findAllByUser(User user);
}
