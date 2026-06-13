package com.gokul.journal.application.repos;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.gokul.journal.application.entity.User;

public interface UserRepository extends MongoRepository<User, ObjectId> {
	
    User findByUserName(String userName);

    void deleteByUserName(String userName);
    
}