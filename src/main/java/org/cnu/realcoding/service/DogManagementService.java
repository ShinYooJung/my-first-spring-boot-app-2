package org.cnu.realcoding.service;

import org.cnu.realcoding.domain.Dog;
import org.cnu.realcoding.exception.DogNotFoundException;
import org.cnu.realcoding.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DogManagementService {

    @Autowired
    private DogRepository dogRepository;

    public void insertDog(Dog dog) {
        dogRepository.insertDog(dog);
    }

    public void updateDog(String name, String ownerName, String ownerPhoneNumber, String newKind) {
        dogRepository.updateDog(name, ownerName, ownerPhoneNumber, newKind);
    }

    public void plusRecord(String name, String ownerName, String ownerPhoneNumber, String newRecord) {
        dogRepository.plusRecord(name, ownerName, ownerPhoneNumber, newRecord);
    }

    public void updateAllDogs(Dog newDog, String oldName, String oldOwnerName, String oldOwnerPhoneNumber) {
        dogRepository.updateAllDogs(newDog, oldName, oldOwnerName, oldOwnerPhoneNumber);
    }

    public Dog getDogByName(String name) {
        Dog dog = dogRepository.findDogByName(name);

        if (dog == null) {
            throw new DogNotFoundException();
        }
        return dog;
    }

    public Dog getDogByOwnerName(String ownerName) {
        Dog dog = dogRepository.findDogByOwnerName(ownerName);

        if (dog == null) {
            throw new DogNotFoundException();
        }
        return dog;
    }

    public Dog getDogByOwnerPhoneNumber(String ownerPhoneNumber) {
        Dog dog = dogRepository.findDogByOwnerPhoneNumber(ownerPhoneNumber);

        if (dog == null) {
            throw new DogNotFoundException();
        }
        return dog;
    }

    public Dog getDogAllInfo(String name, String ownerName, String ownerPhoneNumber) {
        Dog dog = dogRepository.findDogByAllInfo(name, ownerName, ownerPhoneNumber);

        if (dog == null) {
            throw new DogNotFoundException();
        }
        return dog;
    }

    public List<Dog> getAllDogs() {
        return dogRepository.findAllDog();
    }
}