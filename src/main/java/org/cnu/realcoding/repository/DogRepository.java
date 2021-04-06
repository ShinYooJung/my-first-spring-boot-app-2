package org.cnu.realcoding.repository;

import org.cnu.realcoding.domain.Dog;
import org.cnu.realcoding.exception.AlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

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

}
