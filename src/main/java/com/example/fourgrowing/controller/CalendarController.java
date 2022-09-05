package com.example.fourgrowing.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.fourgrowing.data.dto.ProductResponseDto;
import com.example.fourgrowing.data.entity.Product;
import com.example.fourgrowing.repository.ProductRepository;
import com.example.fourgrowing.service.CalendarService;

@Controller
@RequestMapping("/calendar")
public class CalendarController {
    
    @Autowired
    CalendarService calendarService;

    private static Logger logger = LoggerFactory.getLogger(CalendarController.class);

	@RequestMapping
	public String CalendarView(Model model, Principal principal){
    try{
      principal.getName();
    }catch(NullPointerException e){
      return "/front/coordinator";
    }
		List<ProductResponseDto> productList = new ArrayList<ProductResponseDto>();
		productList = calendarService.getProductList(principal.getName());
		model.addAttribute("productList", productList);
		return "/front/coordinator";
	}
    // @GetMapping
    // public String CalendarView(){
    //     return "/front/coordinator";
    // }

    @GetMapping("/event")
    public @ResponseBody List<Map<String, Object>> getEvent(Principal principal, String plantname){
      try{
        principal.getName();
      }catch(NullPointerException e){
        return null;
      }
		  return calendarService.getEventList(principal.getName(), plantname);
    }

}
