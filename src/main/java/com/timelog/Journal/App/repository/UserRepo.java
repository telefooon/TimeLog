package com.timelog.Journal.App.repository;

import com.timelog.Journal.App.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

//will communicate with database
public interface UserRepo extends MongoRepository<User, ObjectId> {

    User findByUsername(String username);
}
