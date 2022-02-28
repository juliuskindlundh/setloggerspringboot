package com.example.setloggersb.security.service;

import com.example.setloggersb.entities.user.User;
import com.example.setloggersb.repositorys.UserRepository;
import com.example.setloggersb.security.UserDetailsImp;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserDetailsServiceImp implements org.springframework.security.core.userdetails.UserDetailsService {

    private UserRepository userRepository;

    public UserDetailsServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmailOrUsername(usernameOrEmail,usernameOrEmail);
        if(optionalUser.isPresent()){
            return UserDetailsImp.build(optionalUser.get());
        }
        throw new UsernameNotFoundException(usernameOrEmail+" could not be found or does not exist");
    }
}
