package com.rijai.LocationApi.service;

import com.rijai.LocationApi.model.*;
import com.rijai.LocationApi.repository.AccountRepository;
import com.rijai.LocationApi.repository.DogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private DogRepository dogRepository;

    @Override 
    public User adoptDog (Dog dog, User user) {
        if (dog.getUser() == null) {
            dog.setUser(user);
            dogRepository.save(dog);
        } else {
            System.out.println("Dog is already adopted by another user.");
        }
        return null;
    }

    @Override
    public List<Dog> getAdoptionRecords (User user) {
        return user.getDogs();
    } 
}
