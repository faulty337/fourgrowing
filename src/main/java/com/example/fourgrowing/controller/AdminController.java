package com.example.fourgrowing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.example.fourgrowing.service.PlantService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	PlantService plantService;

	@GetMapping
	public RedirectView viewAdmin(Model model) {
		return new RedirectView("/admin/product");
	}
	
	@GetMapping("/product")
	public String viewProduct(Model model) {
		// View attribute
		model.addAttribute("plantList", plantService.getPlantList());
		model.addAttribute("template", "admin/product");
		return "admin/index";
	}
	
	@GetMapping("/users")
	public String viewAccount(Model model) {
		// View attribute
		model.addAttribute("template", "admin/users");
		return "admin/index";
	}
	@GetMapping("/plant")
	public String viewPlnat(Model model) {
		// View attribute
		model.addAttribute("template", "admin/plant");
		return "admin/index";
	}
}
