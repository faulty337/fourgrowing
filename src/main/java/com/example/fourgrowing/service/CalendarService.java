package com.example.fourgrowing.service;

import java.io.Console;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fourgrowing.data.dto.ProductResponseDto;
import com.example.fourgrowing.data.entity.Plant;
import com.example.fourgrowing.data.entity.Product;
import com.example.fourgrowing.repository.PlantRepository;
import com.example.fourgrowing.repository.ProductRepository;

@Service
public class CalendarService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    PlantRepository plantRepository;

    ModelMapper modelMapper = new ModelMapper();

    public List<Map<String, Object>> getEventList(String username, String plantname) {
        Product product = productRepository.findByUsernameAndPlantname(username, plantname);
        System.out.print("planttype test : " + product.getPlantType());
        Plant plant = plantRepository.findByPlantType(product.getPlantType());

        int givingWater = plant.getGivingWater();
        int changeWater = plant.getChangeWater();
        LocalDate eventday = product.getPlantingDay();

        List<Map<String, Object>> eventList = new ArrayList<Map<String, Object>>();
		Map<String, Object> event = new HashMap<String, Object>();

        for(int i = 0; i < 365; i+=givingWater){
            eventday = eventday.plusDays(givingWater);
            event.put("start", eventday);
            event.put("title", "물주기");
            event.put("end", eventday);
            eventList.add(event);
            event = new HashMap<String, Object>();
        }

        eventday = LocalDate.now();
        for(int i = 0; i < 365; i+=changeWater){
            eventday = eventday.plusDays(changeWater);
            event.put("start", eventday);
            event.put("title", "물갈이");
            event.put("end", eventday);
            eventList.add(event);
            event = new HashMap<String, Object>();
        }


        return eventList;
    }

    public List<ProductResponseDto> getProductList(String username) {
        return productRepository.findByUsername(username).stream().map(product -> modelMapper.map(product, ProductResponseDto.class)).collect(Collectors.toList());
    }
    
}
