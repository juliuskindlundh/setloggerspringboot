package com.example.setloggersb.dtos;

import lombok.Value;

@Value
public class DataDTO {
    Long id;
    String value;

    public String getValue(){
        return this.value.toLowerCase().trim();
    }
}
