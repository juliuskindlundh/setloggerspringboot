package com.example.setloggersb.dtos;

import com.example.setloggersb.entities.user.User;
import com.example.setloggersb.util.Converter;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class LoginResponseDTO {

    String jwt;
    UserDTO userDTO;
    public LoginResponseDTO(User user, String jwt) {
        this.userDTO = Converter.toDTO(user);
        this.jwt = jwt;
    }
}
