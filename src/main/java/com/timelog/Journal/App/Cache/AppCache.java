package com.timelog.Journal.App.Cache;

import com.timelog.Journal.App.entity.TimeLog_Config;
import com.timelog.Journal.App.repository.TimeLogConfigRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    @Autowired
    private TimeLogConfigRepo timeLogConfigRepo;

    public Map<String, String> APP_CACHE = new HashMap<>();

    @PostConstruct
    public void init(){
       List<TimeLog_Config> all = timeLogConfigRepo.findAll();

       for(TimeLog_Config timeLogConfig : all){
           APP_CACHE.put(timeLogConfig.getKey(), timeLogConfig.getValue());
       }

    }
}
