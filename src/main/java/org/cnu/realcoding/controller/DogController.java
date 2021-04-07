package org.cnu.realcoding.controller;

import org.cnu.realcoding.domain.Dog;
import org.cnu.realcoding.service.DogManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DogController {
    @Autowired
    private DogManagementService dogManagementService;

    @PostMapping("/dogs")
    public void createADog(@RequestBody Dog dog){
        dogManagementService.insertDog(dog);
    }

    @PutMapping("/dogs/{name}/{ownerName}/{ownerPhoneNumber}/{cName}/{cKind}/{cOwnerName}/{cOwnerPhoneNumber}")
    public void updateAllDog(@PathVariable String name, String ownerName, String ownerPhoneNumber, String cName, String cKind, String cOwnerName, String cOwnerPhoneNumber) {
        dogManagementService.updateAllDog(name, ownerName, ownerPhoneNumber, cName, cKind, cOwnerName, cOwnerPhoneNumber);
    }
    @PatchMapping("/dogs/{name}/{ownerName}/{ownerPhoneNumber}/{cKind}")
    public void updateDog(@PathVariable String name, String ownerName, String ownerPhoneNumber, String cKind) {
        dogManagementService.updateDog(name, ownerName, ownerPhoneNumber, cKind);
    }

    @PostMapping("/dogs/{name}/{ownerName}/{ownerPhoneNumber}/{record}")
    public void plusMedicalRecord(@PathVariable String name, String ownerName, String ownerPhoneNumber, String record) {
        dogManagementService.plusRecord(name, ownerName, ownerPhoneNumber, record);
    }

}