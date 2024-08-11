package com.depp.restaurant.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public String greeting(){
        return "welcome to Seti Karnali restaurant. ";
    }
}
