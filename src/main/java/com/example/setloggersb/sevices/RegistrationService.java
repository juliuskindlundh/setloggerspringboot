package com.example.setloggersb.sevices;

import com.example.setloggersb.dtos.LoginDTO;
import com.example.setloggersb.dtos.RegistrationDTO;
import com.example.setloggersb.entities.Category;
import com.example.setloggersb.entities.user.Role;
import com.example.setloggersb.entities.user.User;
import com.example.setloggersb.enums.CategoryEnums;
import com.example.setloggersb.exceptionHandeling.Exceptions;
import com.example.setloggersb.repositorys.CategoryRepository;
import com.example.setloggersb.repositorys.RoleRepository;
import com.example.setloggersb.repositorys.UserRepository;
import com.example.setloggersb.enums.Roles;
import com.example.setloggersb.sevices.entityServices.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Properties;

@Service
public class RegistrationService {
    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;
    LoginService loginService;
    CategoryService categoryService;
    CategoryRepository categoryRepository;

    @Autowired
    RegistrationService(UserRepository userRepository,RoleRepository roleRepository,PasswordEncoder passwordEncoder,LoginService loginService,CategoryService categoryService,CategoryRepository categoryRepository){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.loginService = loginService;
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
    }
    public Object register(RegistrationDTO dto) throws Exception {
        if(userRepository.findByEmail(dto.getEmail()).isPresent()){
            throw new Exceptions.NameNotUnique("Email is already in use");
        }
        if(userRepository.findByUsername(dto.getUsername()).isPresent()){
            throw new Exceptions.NameNotUnique("Username s already in use");
        }
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        Optional<Role> optionalRole = roleRepository.findByName(Roles.USER.name());
        ArrayList<Role> roles = new ArrayList<>();
        if(optionalRole.isPresent()){
            roles.add(optionalRole.get());
            user.setRoles(roles);
        }
        else{
            throw new Exception("rip");
        }

        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        try{
            userRepository.save(user);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }

        Category category = new Category();
        category.setName(CategoryEnums.DEFAULT.name());
        category.setUser(user);
        categoryRepository.save(category);

        return loginService.login(new LoginDTO(user.getEmail(),dto.getPassword()));
    }
}
