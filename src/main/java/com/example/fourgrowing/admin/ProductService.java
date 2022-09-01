package com.example.fourgrowing.admin;

import java.util.List;
import java.util.stream.Collectors;

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
        product.setPlantCode(productCreateDto.getPlantCode());
        product.setPlantType(productCreateDto.getPlantType());
        
        productRepository.save(product);
		return product;
	}
	
	// public String getProductLabelNm(String id) {
	// 	return productRepository.findById(id).getLabelNm();
	// }
}
