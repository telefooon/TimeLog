package com.timelog.Journal.App.controller;

import com.timelog.Journal.App.ApiResponse.WeatherApiResponse;
import com.timelog.Journal.App.entity.JournalEntry;
import com.timelog.Journal.App.entity.User;
import com.timelog.Journal.App.repository.UserRepo;
import com.timelog.Journal.App.service.JournalEntryService;
import com.timelog.Journal.App.service.UserService;
import com.timelog.Journal.App.service.WeatherService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private WeatherService weatherService;

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User userInDb = userService.findByusername(username);
        userInDb.setUsername(user.getUsername());
        userInDb.setPassword(user.getPassword());
        userService.saveNewUser(userInDb);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserbyUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepo.deleteByUsername(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> greetingsAmigos(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherApiResponse response = weatherService.getWeather("Delhi");
        String greetings = "";
        if(response != null){
            greetings = ", Weather "+ response.getCurrent().getFeelslike();
        }
        return new ResponseEntity<>("Hi " + authentication.getName() + greetings,HttpStatus.OK);
    }
}

