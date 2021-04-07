package org.cnu.realcoding.service;

import org.cnu.realcoding.domain.Dog;
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

    public void updateAllDog(String name, String ownerName, String ownerPhoneNumber, String cName, String cKind, String cOwnerName, String cOwnerPhoneNumber) {
        dogRepository.updateAllDog(name, ownerName, ownerPhoneNumber, cName, cKind, cOwnerName, cOwnerPhoneNumber);
    }

    public void updateDog(String name, String ownerName, String ownerPhoneNumber, String cKind) {
        dogRepository.updateDog(name, ownerName, ownerPhoneNumber, cKind);
    }

    public void plusRecord(String name, String ownerName, String ownerPhoneNumber, String record) {
        dogRepository.plusRecord(name, ownerName, ownerPhoneNumber, record);
    }

}