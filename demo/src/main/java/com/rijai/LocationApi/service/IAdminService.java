package com.rijai.LocationApi.service;

import com.rijai.LocationApi.model.Dog;

public interface IAdminService {
    Dog addDog(Dog dog);
    Dog updateDog(long id, Dog dog);
    Dog deleteDog(long id);
}
