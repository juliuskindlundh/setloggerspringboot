package com.example.setloggersb.sevices.entityServices;

import com.example.setloggersb.dtos.CompletedExerciseDTO;
import com.example.setloggersb.dtos.SessionDTO;
import com.example.setloggersb.entities.BufferedCompletedExercise;
import com.example.setloggersb.entities.CompletedExercise;
import com.example.setloggersb.entities.Session;
import com.example.setloggersb.entities.user.User;
import com.example.setloggersb.exceptionHandeling.Exceptions;
import com.example.setloggersb.repositorys.BufferedCompletedExerciseRepository;
import com.example.setloggersb.repositorys.DataRepository;
import com.example.setloggersb.repositorys.SessionRepository;
import com.example.setloggersb.repositorys.UserRepository;
import com.example.setloggersb.security.UserDetailsImp;
import com.example.setloggersb.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SessionService{

    BufferedCompletedExerciseRepository bufferedCompletedExerciseRepository;
    UserRepository userRepository;
    SessionRepository sessionRepository;
    CompletedExerciseService completedExerciseService;
    DataRepository dataRepository;


    @Autowired
    public SessionService(BufferedCompletedExerciseRepository bufferedCompletedExerciseRepository,UserRepository userRepository,
                          SessionRepository sessionRepository,
                          CompletedExerciseService completedExerciseService,
                          DataRepository dataRepository){
        this.bufferedCompletedExerciseRepository = bufferedCompletedExerciseRepository;
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.completedExerciseService = completedExerciseService;
        this.dataRepository = dataRepository;
    }

    public SessionDTO create(SessionDTO sessionDTO) throws Exception {
        throw new Exceptions.NotImplemented("");
    }

    public SessionDTO generateSessionForUser(Long userId) throws Exception{
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()){
            List<Session> userSessionList = sessionRepository.findAllByUser(optionalUser.get());
            bufferedCompletedExerciseRepository.findAllByUser(optionalUser.get()).stream().sorted(BufferedCompletedExercise.getComparator()).forEach(bce->{

                int i = 0;
                boolean createNew = true;
                while(i < userSessionList.size()){
                        long mt = (userSessionList.get(i).getFistCompletedExercise() + userSessionList.get(i).getLastCompletedExercise()) / 2 ;
                        long dt = Math.abs(mt - bce.getTimeStamp());
                        if(dt <= optionalUser.get().getSessionTimeDiffSetting()){
                            System.out.println("dt = "+dt);
                            try {
                                userSessionList.get(i).addCompletedExercise(completedExerciseService.create(bce),bce.getTimeStamp());
                                bufferedCompletedExerciseRepository.delete(bce);
                                sessionRepository.save(userSessionList.get(i));
                                createNew = false;
                                break;
                            } catch (Exception e) {
                                //do nothing ?
                            }
                        }
                    i++;
                }
                if(createNew){
                    Session session = new Session();
                    session.setUser(optionalUser.get());
                    session.setFistCompletedExercise(bce.getTimeStamp());
                    session.setLastCompletedExercise(bce.getTimeStamp());
                    try {
                        session.addCompletedExercise(completedExerciseService.create(bce),bce.getTimeStamp());
                        bufferedCompletedExerciseRepository.delete(bce);
                        sessionRepository.save(session);
                        userSessionList.add(session);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

        }
        else{
            throw new Exceptions.EntityNotFoundException("userId: "+userId);
        }
        return null;
    }


    public SessionDTO.Response getById(Long id) throws Exception {
        throw new Exceptions.NotImplemented("");
    }

    public List<SessionDTO.Response> getAll() {
        throw new Exceptions.NotImplemented("");
    }

    public SessionDTO update(SessionDTO sessionDTO) throws Exception {
        return null;
    }

    public void deleteById(Long id) throws Exception {

    }

    public List<SessionDTO.Response> findAllByUserId(UserDetailsImp userDetailsImp) {
        List<SessionDTO> dtos = sessionRepository.findAllByUser(userRepository.getById(userDetailsImp.getId())).stream().map(Converter::toDTO).collect(Collectors.toList());
        List<SessionDTO.Response> responseDTOs = new ArrayList<>();
        dtos.forEach(dto->{

            ArrayList<CompletedExerciseDTO.Response> list = new ArrayList<>();
            dto.getCompletedExercises().forEach(ce->{
                list.add(new CompletedExerciseDTO.Response(ce.getId(),Converter.toDTO(ce.getExercise()),dataRepository.findAllById(ce.getDataIds()).stream().map(Converter::toDTO).collect(Collectors.toList())));
            });
            SessionDTO.Response temp = new SessionDTO.Response(dto.getId(),dto.getFistCompletedExercise(),dto.getLastCompletedExercise(),list);
            responseDTOs.add(temp);
        });
        return responseDTOs;
    }
}
