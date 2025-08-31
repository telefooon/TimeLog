package com.timelog.Journal.App.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "TimeLog_Config")
@Data
@NoArgsConstructor
public class TimeLog_Config {

    private String key;
    private String value;
}
