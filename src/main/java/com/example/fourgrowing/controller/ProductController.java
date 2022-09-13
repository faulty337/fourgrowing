package com.example.fourgrowing.controller;

import java.security.Principal;
import java.util.List;

import com.example.fourgrowing.data.dto.ProductCreateDto;
import com.example.fourgrowing.data.dto.ProductResponseDto;
import com.example.fourgrowing.data.dto.RegisterResponsDto;
import com.example.fourgrowing.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
	private ProductService productService;

	@GetMapping
	public @ResponseBody List<ProductResponseDto> getProduct() {
		List<ProductResponseDto> result = productService.getProduct();
		return result;
	}
	
	@PostMapping
	public RedirectView newProduct(@ModelAttribute ProductCreateDto productCreateDto) {
		productService.newProduct(productCreateDto);
		return new RedirectView("/admin/product");
	}

	@GetMapping("/registerForm")
    public String registerForm(Model model, Principal principal) {
        return "front/registerProduct";
    }

	@PostMapping("/registerProduct")
	public String registerProduct(Model model,  Principal principal, String plantCode, String plantname){
		RegisterResponsDto registerResponsDto = productService.setUsername(principal.getName(), plantCode, plantname);
		model.addAttribute("registerRespons", registerResponsDto.getResult());
		model.addAttribute("errorMessage", registerResponsDto.getErrorCode());
		return "front/registerProduct";
	}
}
