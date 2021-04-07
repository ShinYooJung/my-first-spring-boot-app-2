package org.cnu.realcoding.repository;

import org.cnu.realcoding.domain.Dog;
import org.cnu.realcoding.exception.AlreadyExistsException;
import org.cnu.realcoding.exception.DogNotFoundException;
import org.cnu.realcoding.exception.RejectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

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


    public Dog findDogByName(String name) {
        return mongoTemplate.findOne(Query.query(Criteria.where("name").is(name)),Dog.class);
    }

    public Dog findDogByOwnerName(String OwnerName) {
        return mongoTemplate.findOne(Query.query(Criteria.where("ownerName").is(OwnerName)),Dog.class);
    }

    public Dog findDogByOwnerPhoneNumber(String OwnerPhoneNumber) {
        return mongoTemplate.findOne(Query.query(Criteria.where("ownerPhoneNumber").is(OwnerPhoneNumber)),Dog.class);
    }
    public Dog findDogByAllInfo(String name, String Ownername, String OwnerPhoneNumber) {
        return mongoTemplate.findOne(Query.query(Criteria.where("name").is(name).and("ownerName").is(Ownername).and("ownerPhoneNumber").is(OwnerPhoneNumber)), Dog.class);

    public void updateAllDogs(Dog newDog, String oldName, String oldOwnerName, String oldOwnerPhoneNumber) {
        Criteria criteria = Criteria.where("name").is(oldName).and("ownerName").is(oldOwnerName).and("ownerPhoneNumber").is(oldOwnerPhoneNumber);
        Criteria criteria2 = Criteria.where("name").is(newDog.getName()).and("ownerName").is(newDog.getOwnerName()).and("ownerPhoneNumber").is(newDog.getOwnerPhoneNumber());
        Query query = new Query(criteria);
        Query query2 = new Query(criteria2);
        if(!mongoTemplate.exists(query, Dog.class)) {
            throw new DogNotFoundException(); //찾을 수 없는 정보
        }
        if(mongoTemplate.exists(query2, Dog.class)) {
            throw new AlreadyExistsException(); //이미 존재하는 unique keyword
        }
        if(newDog.getMedicalRecords() != null) {
            throw new RejectException(); // 기존에 입력된 진료기록을 수정하려고 함
        }
        Update update = new Update();
        update.set("name", newDog.getName());
        update.set("kind", newDog.getKind());
        update.set("ownerName", newDog.getOwnerName());
        update.set("ownerPhoneNumber", newDog.getOwnerPhoneNumber());
        mongoTemplate.updateFirst(query, update, Dog.class);
    }

    public void updateDog(String name, String ownerName, String ownerPhoneNumber, String newKind) {
        Criteria criteria = Criteria.where("name").is(name).and("ownerName").is(ownerName).and("ownerPhoneNumber").is(ownerPhoneNumber);
        Query query = new Query(criteria);
        if(!mongoTemplate.exists(query, Dog.class)) {
            throw new DogNotFoundException();
        }
        Update update = new Update();
        update.set("kind", newKind);
        mongoTemplate.updateFirst(query, update, Dog.class);
    }

    public void plusRecord(String name, String ownerName, String ownerPhoneNumber, String newRecord) {
        Criteria criteria = Criteria.where("name").is(name).and("ownerName").is(ownerName).and("ownerPhoneNumber").is(ownerPhoneNumber);
        Query query = new Query(criteria);
        if(!mongoTemplate.exists(query, Dog.class)) {
            throw new DogNotFoundException();
        }
        Dog dog = new Dog();
        dog = mongoTemplate.findAndRemove(query, Dog.class);
        List<String> list = new ArrayList<>();
        list = dog.getMedicalRecords();
        list.add(newRecord);
        dog.setMedicalRecords(list);
        mongoTemplate.insert(dog);
    }

    public List<Dog> findAllDog() {
        return mongoTemplate.findAll(Dog.class);
    }

}
