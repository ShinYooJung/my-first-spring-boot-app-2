package org.cnu.realcoding.repository;

import org.cnu.realcoding.domain.Dog;
import org.cnu.realcoding.exception.AlreadyExistsException;
import org.cnu.realcoding.exception.DogNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class DogRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void insertDog(Dog dog){
        Query query = new Query();
        Criteria criteria = Criteria.where("name").is(dog.getName()).and("ownerName").is(dog.getOwnerName()).and("ownerPhoneNumber").is(dog.getOwnerPhoneNumber());
        query.addCriteria(criteria);
        if(mongoTemplate.exists(query, Dog.class)){
            throw new AlreadyExistsException();
        }
        mongoTemplate.insert(dog);
    }

    public void updateAllDog(String name, String ownerName, String ownerPhoneNumber, String cName, String cKind, String cOwnerName, String cOwnerPhoneNumber) {
        Criteria criteria = Criteria.where("name").is(name).and("ownerName").is(ownerName).and("ownerPhoneNumber").is(ownerPhoneNumber);
        Criteria criteria2 = Criteria.where("name").is(cName).and("ownerName").is(cOwnerName).and("ownerPhoneNumber").is(cOwnerPhoneNumber);
        Query query = new Query(criteria);
        Query query2 = new Query(criteria2);
        if(!mongoTemplate.exists(query, Dog.class)) {
            throw new DogNotFoundException();
        }
        if(mongoTemplate.exists(query2, Dog.class)) {
            throw new AlreadyExistsException();
        }
        Update update = new Update();
        update.set("name", cName);
        update.set("kind", cKind);
        update.set("ownerName", cOwnerName);
        update.set("ownerPhoneNumber", cOwnerPhoneNumber);
        mongoTemplate.updateFirst(query, update, Dog.class);
    }

    public void updateDog(String name, String ownerName, String ownerPhoneNumber, String cKind) {
        Criteria criteria = Criteria.where("name").is(name).and("ownerName").is(ownerName).and("ownerPhoneNumber").is(ownerPhoneNumber);
        Query query = new Query(criteria);
        if(!mongoTemplate.exists(query, Dog.class)) {
            throw new DogNotFoundException();
        }
        Update update = new Update();
        update.set("kind", cKind);
        mongoTemplate.updateFirst(query, update, Dog.class);
    }

    public void plusRecord(String name, String ownerName, String ownerPhoneNumber, String record) {
        Criteria criteria = Criteria.where("name").is(name).and("ownerName").is(ownerName).and("ownerPhoneNumber").is(ownerPhoneNumber);
        Query query = new Query(criteria);
        if(!mongoTemplate.exists(query, Dog.class)) {
            throw new DogNotFoundException();
        }
        Dog dog = new Dog();
        dog = mongoTemplate.findAndRemove(query, Dog.class);
        List<String> list = new ArrayList<>();
        list = dog.getMedicalRecords();
        list.add(record);
        dog.setMedicalRecords(list);
        mongoTemplate.insert(dog);
    }

}