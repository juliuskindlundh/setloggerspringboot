package com.example.setloggersb.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table()
public class InputField {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String definition;


    public InputField(String name, String definition){
        this.name = name;
        this.definition = definition;
    }
}
