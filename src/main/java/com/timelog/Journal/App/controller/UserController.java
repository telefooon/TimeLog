package com.timelog.Journal.App.controller;

import com.timelog.Journal.App.entity.JournalEntry;
import com.timelog.Journal.App.entity.User;
import com.timelog.Journal.App.service.JournalEntryService;
import com.timelog.Journal.App.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAll();
    }

    @PostMapping
    public void createUser(@RequestBody User user){
        userService.saveEntry(user);
    }

    @PutMapping("/{username}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String username){
       User userInDb = userService.findByusername(username);

       if(userInDb != null){
           userInDb.setUsername(user.getUsername());
           userInDb.setPassword(user.getPassword());
           userService.saveEntry(userInDb);
       }
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

