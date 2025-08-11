package com.timelog.Journal.App.service;

import com.timelog.Journal.App.entity.User;
import com.timelog.Journal.App.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public void saveEntry(User user){
        userRepo.save(user);
    }

    public List<User> getAll(){
        return userRepo.findAll();
    }

    public Optional<User> findbyId(ObjectId id){
        return userRepo.findById(id);
    }

    public void deletebyId(ObjectId id){
        userRepo.deleteById(id);
    }

    public User findByusername(String username){
        return userRepo.findByUsername(username);
    }
}
