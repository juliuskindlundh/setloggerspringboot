package com.example.setloggersb.entities;

import com.example.setloggersb.repositorys.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@lombok.Data
public class CompletedExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Exercise exercise;
    @ElementCollection(fetch = FetchType.EAGER)
    @OrderColumn
    List<Long> dataIds = new ArrayList<>();
}
