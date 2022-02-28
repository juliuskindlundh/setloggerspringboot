package com.example.setloggersb.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<InputField> inputFields;

    public boolean inputFieldsEquals(List<String> inputFields) {
        if(inputFields.size() != this.inputFields.size()){
            return false;
        }
        for(int i = 0; i < this.getInputFields().size();i++){
            if(!this.inputFields.get(i).getName().equals(inputFields.get(i))){
                return false;
            }
        }
        return true;
    }
}
