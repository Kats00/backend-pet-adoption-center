package com.rijai.LocationApi.service;

import com.rijai.LocationApi.model.Dog;
import com.rijai.LocationApi.model.User;

import java.util.List;

public interface IUserService {
    User adoptDog (Dog dog, User user);
    List<Dog> getAdoptionRecords(User user);
}