package com.example.setloggersb.entities.user;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    @Column(unique = true)
    private String email;
    @ManyToMany//(fetch = FetchType.EAGER)
    private List<Role> roles;
    @ColumnDefault("14400000") // default 4h 1000x60x60x4
    private Long sessionTimeDiffSetting = 14400000L;
}
