package org.cnu.realcoding.repository;

import org.cnu.realcoding.domain.Dog;
import org.cnu.realcoding.exception.AlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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
        if(mongoTemplate.exists(query,Dog.class)){
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
    }

    public List<Dog> findAllDog() {
        return mongoTemplate.findAll(Dog.class);
    }
}
