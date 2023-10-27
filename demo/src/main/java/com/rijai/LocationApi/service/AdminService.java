package com.rijai.LocationApi.service;

import com.rijai.LocationApi.model.Dog;
import com.rijai.LocationApi.repository.DogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService implements IAdminService {
    @Autowired
    private DogRepository repository;

    public Dog addDog(Dog dog) {
        return repository.save(dog);
    }

    public Dog updateDog(long id, Dog updatedDog) {
        Optional<Dog> existingDog = repository.findById(id);
    
        if (existingDog.isPresent()) {
            Dog dogToUpdate = existingDog.get();
            dogToUpdate.setName(updatedDog.getName());
            dogToUpdate.setBreed(updatedDog.getBreed());
            dogToUpdate.setHealth(updatedDog.getHealth());
            dogToUpdate.setAge(updatedDog.getAge());
            dogToUpdate.setDistance(updatedDog.getDistance());
            return repository.save(dogToUpdate);
        } else {
            System.out.println("Error updating dog with id: " + id);
            return null;
        }
    }
    

    public Dog deleteDog(long id)
    {
        Optional<Dog> dog = repository.findById(id);
        if(dog.isPresent()) {
            repository.delete(dog.get());
            System.out.println("Successfully deleted dog: " +  id);
            return null;
        } else {
            System.out.println("No such dog with id: " +  id);
            return null;
        }
    }
}
