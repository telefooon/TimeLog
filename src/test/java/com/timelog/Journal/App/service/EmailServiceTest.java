package com.timelog.Journal.App.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {
    @Autowired
    private EmailService emailService;

    @Test
    @Disabled
    void tosendemail(){
        emailService.sendEmail("khanfaaeiz@gmail.com","testing email","email body");
    }
}
