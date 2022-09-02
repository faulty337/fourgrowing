package com.example.fourgrowing.service;

import java.util.List;
import java.util.stream.Collectors;

import com.example.fourgrowing.data.entity.Product;
import com.example.fourgrowing.data.dto.ProductCreateDto;
import com.example.fourgrowing.data.dto.ProductResponseDto;
import com.example.fourgrowing.repository.ProductRepository;
import org.modelmapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
	private ProductRepository productRepository;

	private ModelMapper modelMapper = new ModelMapper();
	
	public List<ProductResponseDto> getProduct(){
		return productRepository.findAll().stream().map(product -> modelMapper.map(product, ProductResponseDto.class)).collect(Collectors.toList());
	}
	
	public Product newProduct(ProductCreateDto productCreateDto) {
        Product product = new Product();
        product.setPlantCode(productCreateDto.getPlantcode());
        product.setPlantType(productCreateDto.getPlanttype());
        
        productRepository.save(product);
		return product;
	}
	
	// public String getProductLabelNm(String id) {
	// 	return productRepository.findById(id).getLabelNm();
	// }
}
