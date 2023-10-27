package com.rijai.LocationApi.service;

import com.rijai.LocationApi.model.Dog;

import java.util.List;

public interface IDogService {
    List<Dog> getDogs();
    //Dog addDog(Dog dog);
    //Dog updateDog(long id, Dog dog);
    Dog getDog(long id);
    //Dog deleteDog(long id);
}