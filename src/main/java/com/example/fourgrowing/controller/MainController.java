package com.example.fourgrowing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/")
    public String root(){
        return "front/index";
    }

    @RequestMapping("/recipe")
    public String recipe(){
        return "front/menu";
    }

}
