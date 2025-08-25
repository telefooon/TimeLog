package com.timelog.Journal.App.service;

import com.timelog.Journal.App.entity.User;
import com.timelog.Journal.App.repository.UserRepo;
import com.timelog.Journal.App.service.UserDetailServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;

import static  org.mockito.Mockito.*;

@ActiveProfiles("dev")
public class UserDetailsServiceImplTests {

    @InjectMocks
    private UserDetailServiceImpl userDetailsService;

    @Mock
    private UserRepo userRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Disabled
    @Test
    void loadUserByUsernameTest(){
        when(userRepository.findByUsername(ArgumentMatchers.anyString())).thenReturn(User.builder().username("ram").password("inrinrick").roles(new ArrayList<>()).build());
        UserDetails user = userDetailsService.loadUserByUsername("ram");
        Assertions.assertNotNull(user);
    }
}