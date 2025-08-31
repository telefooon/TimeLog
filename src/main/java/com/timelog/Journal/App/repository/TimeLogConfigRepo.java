package com.timelog.Journal.App.repository;

import com.timelog.Journal.App.entity.JournalEntry;
import com.timelog.Journal.App.entity.TimeLog_Config;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

//will communicate with database
public interface TimeLogConfigRepo extends MongoRepository<TimeLog_Config, ObjectId> {
}
