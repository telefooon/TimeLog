package com.timelog.Journal.App.repository;

import com.timelog.Journal.App.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
//will communicate with database
public interface JournalEntryRepo extends MongoRepository<JournalEntry, ObjectId> {
}
