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
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    @Autowired
        private JournalEntryService journalEntryService;

    @Autowired
        private UserService userService;
        @GetMapping("{username}")
        public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String username){

            User user = userService.findByusername(username);
            List<JournalEntry> all = user.getJournalEntries();

            if(all != null && !all.isEmpty()){
                return new ResponseEntity<>(all, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        @PostMapping("{username}")
        public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry, @PathVariable String username){
            try{
                journalEntryService.saveEntry(myEntry, username);
                return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

        }

        @GetMapping("id/{myId}")
        public ResponseEntity<JournalEntry> getJournalById(@PathVariable ObjectId myId){
            Optional<JournalEntry> journalEntry = journalEntryService.findbyId(myId);
            if(journalEntry.isPresent()){
                return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);


        }

        @DeleteMapping("id/{username}/{myId}")
        public ResponseEntity<?> DeleteJournalById(@PathVariable ObjectId myId, @PathVariable String username){
            journalEntryService.deletebyId(myId, username);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        @PutMapping("/id/{username}/{id}")
        public ResponseEntity<?> updateJournalById(@PathVariable ObjectId id,
                                                   @RequestBody JournalEntry newEntry,
                                                   @PathVariable String username){

            JournalEntry old = journalEntryService.findbyId(id).orElse(null);
            if(old != null){
                old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
                old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
                journalEntryService.saveEntry(old);  // Assign the returned value
                return new ResponseEntity<>(old, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

