package com.example.fourgrowing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import com.example.fourgrowing.data.dto.PlantCreateDto;
import com.example.fourgrowing.data.dto.PlantResponseDto;
import com.example.fourgrowing.service.PlantService;

@Controller
@RequestMapping("/plant")
public class PlantController {
    @Autowired
    PlantService plantService;


    @GetMapping("/getPlantList")
    public @ResponseBody List<PlantResponseDto> getPlantList(){
        return plantService.getPlantList();
    }

    @PostMapping
    public RedirectView createPlant(@ModelAttribute PlantCreateDto plantCreateDto){
        plantService.createPlant(plantCreateDto);
        return new RedirectView("/admin/plant");
    }
}
