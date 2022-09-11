package com.example.fourgrowing.controller;

import java.security.Principal;
import java.util.List;

import com.example.fourgrowing.data.dto.ProductCreateDto;
import com.example.fourgrowing.data.dto.ProductResponseDto;
import com.example.fourgrowing.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public String registerForm(Principal principal) {
		
        return "account/registerProduct";
    }

	@GetMapping("/registerProduct")
	public String registerProduct(Principal principal, String plantCode){
		productService.setUsername(principal.getName(), plantCode);
		return null;
	}
}
