package com.example.fourgrowing.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Map<String, Object>> getEventList(String username, String plantname) {
        Product product = productRepository.findByUsername(username);
        Plant plant = plantRepository.findByPlantType(product.getPlantType());

        int givingWater = plant.getGivingWater();
        int changeWater = plant.getChangeWater();
        LocalDate eventday = product.getPlantingDay();
        
        // int givingWater = 5;
        // int changeWater = 30;
        // LocalDate eventday = LocalDate.now();

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
    
}
