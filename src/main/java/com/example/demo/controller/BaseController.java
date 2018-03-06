package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;

@Controller
public class BaseController {

    @Autowired
    Environment environment;

    protected String activeProfile;

    @PostConstruct
    public void getActiveProfile() {
        activeProfile = environment.getProperty("spring.profiles.active").toLowerCase();
    }


}
