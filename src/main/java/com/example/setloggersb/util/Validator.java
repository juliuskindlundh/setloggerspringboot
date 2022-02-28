package com.example.setloggersb.util;

import com.example.setloggersb.exceptionHandeling.Exceptions;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class Validator {

    private static EntityManager entityManager;

    public Validator(EntityManager entityManager){
        Validator.entityManager = entityManager;
    }

    public static <T> void isReal(Class<T> entity,Long... ids){
        for(Long id: ids) {
            T object = entityManager.find(entity,id);
            if(object == null){
                throw new Exceptions.EntityNotFoundException(entity.getName()+"Id: "+id);
            }
        }
    }
}
