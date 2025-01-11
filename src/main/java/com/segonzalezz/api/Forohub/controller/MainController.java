package com.segonzalezz.api.Forohub.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controller")
public class MainController {

    @GetMapping
    public String helloWorld(){
        return "Hello World";
    }

}
