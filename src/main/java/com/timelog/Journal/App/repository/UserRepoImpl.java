package com.timelog.Journal.App.repository;

import com.timelog.Journal.App.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class UserRepoImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> getUserForSA () {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").exists(true).regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"));
        query.addCriteria(Criteria.where("sentimentAnalysis").exists(true));
        List<User> users = mongoTemplate.find(query, User.class);
        return users;
    }
}
