package com.example.setloggersb.dtos;

import com.example.setloggersb.entities.user.Role;
import lombok.AllArgsConstructor;
import lombok.Value;

import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.List;

@Value
@AllArgsConstructor
public class UserDTO {
    Long id;
    String username;
    String email;
    @ManyToMany(fetch = FetchType.EAGER)
    List<Role> roles;
}
