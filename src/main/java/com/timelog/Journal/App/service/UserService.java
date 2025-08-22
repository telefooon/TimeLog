package com.timelog.Journal.App.service;

import com.timelog.Journal.App.entity.User;
import com.timelog.Journal.App.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    private static final PasswordEncoder password = new BCryptPasswordEncoder();
    @Autowired
    private UserRepo userRepo;

    public void saveEntry(User user){

        user.setPassword(password.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
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
