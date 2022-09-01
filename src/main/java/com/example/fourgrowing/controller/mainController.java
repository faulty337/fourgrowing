package com.example.fourgrowing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class mainController {

    @RequestMapping("/")
    public String root(){
        return "index";
    }
}
