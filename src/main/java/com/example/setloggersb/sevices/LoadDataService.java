package com.example.setloggersb.sevices;

import com.example.setloggersb.entities.InputField;
import com.example.setloggersb.entities.user.Role;
import com.example.setloggersb.enums.EInputType;
import com.example.setloggersb.repositorys.InputFieldRepository;
import com.example.setloggersb.repositorys.RoleRepository;
import com.example.setloggersb.enums.Roles;
import com.example.setloggersb.repositorys.UserRepository;
import com.example.setloggersb.sevices.entityServices.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoadDataService implements CommandLineRunner {

    RoleRepository roleRepository;
    InputFieldRepository inputFieldRepository;
    UserRepository userRepository;
    SessionService sessionService;

    @Autowired
    public LoadDataService(RoleRepository roleRepository,InputFieldRepository inputFieldRepository,UserRepository userRepository,SessionService sessionService){
        this.roleRepository = roleRepository;
        this.inputFieldRepository = inputFieldRepository;
        this.userRepository = userRepository;
        this.sessionService = sessionService;
    }

    @Override
    public void run(String... args) throws Exception {
        loadRoles();
        loadInputFields();
        //sessionService.generateSessionForUser(9L);
    }

    private void loadInputFields() {
        Optional<InputField> inputField;
        inputField = inputFieldRepository.findByName(EInputType.M.name());
        if(inputField.isEmpty()){
            inputFieldRepository.save(new InputField(EInputType.M.name(),""));
        }
        inputField = inputFieldRepository.findByName(EInputType.KM.name());
        if(inputField.isEmpty()){
            inputFieldRepository.save(new InputField(EInputType.KM.name(),""));
        }
        inputField = inputFieldRepository.findByName(EInputType.MIN.name());
        if(inputField.isEmpty()){
            inputFieldRepository.save(new InputField(EInputType.MIN.name(),""));
        }
        inputField = inputFieldRepository.findByName(EInputType.H.name());
        if(inputField.isEmpty()){
            inputFieldRepository.save(new InputField(EInputType.H.name(),""));
        }
        inputField = inputFieldRepository.findByName(EInputType.KG.name());
        if(inputField.isEmpty()){
            inputFieldRepository.save(new InputField(EInputType.KG.name(),""));
        }
        inputField = inputFieldRepository.findByName(EInputType.NUM.name());
        if(inputField.isEmpty()){
            inputFieldRepository.save(new InputField(EInputType.NUM.name(),""));
        }
    }

    private void loadRoles(){
        Optional<Role> role;
        role = roleRepository.findByName(Roles.USER.name());
        if(role.isEmpty()){
            roleRepository.save(new Role(Roles.USER.name()));
        }
        role = roleRepository.findByName(Roles.ADMIN.name());
        if(role.isEmpty()){
            roleRepository.save(new Role(Roles.ADMIN.name()));
        }
    }
}
