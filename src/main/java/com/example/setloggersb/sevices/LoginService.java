package com.example.setloggersb.sevices;

import com.example.setloggersb.dtos.LoginDTO;
import com.example.setloggersb.dtos.LoginResponseDTO;
import com.example.setloggersb.entities.user.User;
import com.example.setloggersb.exceptionHandeling.Exceptions;
import com.example.setloggersb.repositorys.UserRepository;
import com.example.setloggersb.security.component.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service@Lazy
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    UserRepository userRepository;

    @Autowired
    public LoginService(AuthenticationManager authenticationManager,JwtTokenUtil jwtTokenUtil, UserRepository userRepository){
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userRepository = userRepository;
    }

    public Object login(LoginDTO dto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmailOrUsername(),dto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenUtil.generateToken(authentication);
        Optional<User> userOptional = userRepository.findByEmailOrUsername(dto.getEmailOrUsername(), dto.getEmailOrUsername());
        if(userOptional.isEmpty()){
            throw new Exceptions.CouldNotLogIn("Username does not exist or password is wrong");
        }
        return new LoginResponseDTO(userOptional.get(),jwt);
    }
}
