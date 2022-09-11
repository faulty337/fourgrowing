package com.example.fourgrowing.service;

import java.util.List;
import java.util.stream.Collectors;

import com.example.fourgrowing.data.entity.Product;
import com.example.fourgrowing.data.dto.ProductCreateDto;
import com.example.fourgrowing.data.dto.ProductResponseDto;
import com.example.fourgrowing.repository.ProductRepository;
import com.google.common.base.CharMatcher;

import org.modelmapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

@Service
public class ProductService {
    @Autowired
	private ProductRepository productRepository;

	private ModelMapper modelMapper = new ModelMapper();
	
	public List<ProductResponseDto> getProduct(){
		return productRepository.findAll().stream().map(product -> modelMapper.map(product, ProductResponseDto.class)).collect(Collectors.toList());
	}
	
	public Product newProduct(ProductCreateDto productCreateDto) {
        Product product = Product.builder().plantCode(productCreateDto.getPlantcode()).plantType(productCreateDto.getPlanttype()).build();
        productRepository.save(product);
		Product insertProduct = productRepository.findByPlantCode(productCreateDto.getPlantcode());
		product = Product.builder().id(insertProduct.getId()).plantType(insertProduct.getPlantType()).plantCode(plantCodeIncoding(insertProduct.getId())).build();
		productRepository.save(product);
		

		return product;
	}

	private String plantCodeIncoding(Long id){
		String str = "product";
		int idSize = 0;
		for(long i = id; i > 10; i /= 10){
			idSize++;
		}
		String result = new String(Base64Utils.encode((str.substring(0, str.length() - idSize) + id).getBytes()));


		return result;
	}

	public Product setUsername(String name, String plantCode) {
		
		String strBase64Encode = new String(Base64Utils.decode(plantCode.getBytes()));
		String charsToRetain = "0123456789"; 
		Long productId = Long.parseLong(CharMatcher.anyOf(charsToRetain).retainFrom(strBase64Encode));
		Product product = productRepository.findById(productId).get();
		product.setUsername(name);
		product = productRepository.save(product);
		return product;
	}

	// public RegistrationResponsDto registrationUser(String username, String ProductCode){
	// 	RegistrationResponsDto registrationResponsDto = new RegistrationResponsDto();
	// 	try{
	// 		productRepository.findByPlantCode()
	// 	}
		

	// 	return registrationResponsDto;
	// }
	
	// public String getProductLabelNm(String id) {
	// 	return productRepository.findById(id).getLabelNm();
	// }
}
