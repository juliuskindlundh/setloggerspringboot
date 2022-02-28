package com.example.setloggersb.dtos;

import lombok.Value;

@Value
public class LoginDTO {
    String emailOrUsername;
    String password;
}
