package com.Niharika.social.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class homeController {
    @GetMapping
    public String homeControllerHandler(){
       return "Hello, World";
    }
}
