package com.example.setloggersb.entities;

import com.example.setloggersb.entities.user.User;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long parentId;
    private String name;
    @ElementCollection
    private List<Long> childIds = new ArrayList<>();
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Exercise> exercises = new ArrayList<>();
    @ManyToOne
    private User user;
}
