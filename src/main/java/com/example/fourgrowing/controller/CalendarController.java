package com.example.fourgrowing.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.fourgrowing.service.CalendarService;

@Controller
@RequestMapping("/calendar")
public class CalendarController {
    
    @Autowired
    CalendarService calendarService;


    @GetMapping
    public String CalendarView(){
        return "/front/coordinator";
    }

    @GetMapping("/event")
    public @ResponseBody List<Map<String, Object>> getEvent(Principal principal, String plantName){
        List<Map<String, Object>> list = calendarService.getEventList(principal.getName(), plantName);
		// List<Map<String, Object>> eventList = new ArrayList<Map<String, Object>>();
		// Map<String, Object> event = new HashMap<String, Object>();
		// event.put("start", "2022-09-03");
		// event.put("title", "test1");
		// event.put("end", "2022-09-03");
		// eventList.add(event);
		// event = new HashMap<String, Object>();
		// event.put("start", "2022-09-04");
		// event.put("title", "test2");
		// event.put("end", "2022-09-04");
		// eventList.add(event);
		return list;


    }

}
