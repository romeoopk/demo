package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
class WelcomeController extends BaseController {

    @RequestMapping("/")
    public String hello() throws UnknownHostException {
        return "Hello World! from "+ InetAddress.getLocalHost().getHostName()+" running against :: <strong>"+activeProfile+"</strong> environment!!!";
    }
}