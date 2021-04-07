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

    @PutMapping("/dogs/{oldName}/{oldOwnerName}/{oldOwnerPhoneNumber}")
    public void updateAllDogs(@RequestBody Dog newDog, @PathVariable String oldName, String oldOwnerName, String oldOwnerPhoneNumber) {
        dogManagementService.updateAllDogs(newDog, oldName, oldOwnerName, oldOwnerPhoneNumber);
    }
    @PatchMapping("/dogs/{name}/{ownerName}/{ownerPhoneNumber}/{newKind}")
    public void updateDog(@PathVariable String name, String ownerName, String ownerPhoneNumber, String newKind) {
        dogManagementService.updateDog(name, ownerName, ownerPhoneNumber, newKind);
    }

    @PostMapping("/dogs/{name}/{ownerName}/{ownerPhoneNumber}/{newRecord}")
    public void plusMedicalRecord(@PathVariable String name, String ownerName, String ownerPhoneNumber, String newRecord) {
        dogManagementService.plusRecord(name, ownerName, ownerPhoneNumber, newRecord);
    }

    @GetMapping("/dogs")
    public List<Dog> getAllDogs() {
        return dogManagementService.getAllDogs();
    }

    @GetMapping("/dog/{name}")
    public Dog getDogByName(@PathVariable String name) {
        return dogManagementService.getDogByName(name);
    }

    @GetMapping("/dog/ownerName/{ownerName}")
    public Dog getDogByOwnerName(@PathVariable String ownerName) {
        return dogManagementService.getDogByOwnerName(ownerName);
    }

    @GetMapping("/dog/ownerPhoneNumber/{ownerPhoneNumber}")
    public Dog getDogByOwnerPhoneNumber (@PathVariable String ownerPhoneNumber) {
        return dogManagementService.getDogByOwnerPhoneNumber (ownerPhoneNumber);
    }

    @GetMapping("/dog/{name}/{ownerName}/{ownerPhoneNumber}")
    public Dog getDogAllInfo (@PathVariable String name, String ownerName, String ownerPhoneNumber) {
        return dogManagementService.getDogAllInfo (name, ownerName, ownerPhoneNumber);
    }


}