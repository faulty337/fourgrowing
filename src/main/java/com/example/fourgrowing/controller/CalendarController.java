package com.example.fourgrowing.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.fourgrowing.data.dto.ProductResponseDto;
import com.example.fourgrowing.service.CalendarService;

@Controller
@RequestMapping("/calendar")
public class CalendarController {
    
    @Autowired
    CalendarService calendarService;


	@RequestMapping
	public String CalendarView(Model model, Principal principal){
    //로그인이 되어 있지 않을 시 Nullpointer 에러가 발생
    try{
      principal.getName();
    }catch(NullPointerException e){
      return "/front/coordinator";
    }
		List<ProductResponseDto> productList = new ArrayList<ProductResponseDto>();
		productList = calendarService.getProductList(principal.getName());
		model.addAttribute("productList", productList); //현재 로그인한 유저의 상품 정보를 같이 전송
		return "/front/coordinator";
	}

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
