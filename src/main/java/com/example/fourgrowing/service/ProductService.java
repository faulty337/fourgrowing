package com.example.fourgrowing.service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import com.example.fourgrowing.data.entity.Product;
import com.example.fourgrowing.data.dto.ProductCreateDto;
import com.example.fourgrowing.data.dto.ProductResponseDto;
import com.example.fourgrowing.data.dto.RegisterResponsDto;
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
		product = Product.builder()
			.id(insertProduct.getId())
			.plantType(insertProduct.getPlantType())
			.plantCode(plantCodeIncoding(insertProduct.getId()).replace("=", ""))
			.plantingDay(LocalDate.now())
			.build();
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

	public RegisterResponsDto setUsername(String name, String plantCode, String plantname) {
		String strBase64Encode = "";
		RegisterResponsDto registerResponsDto = new RegisterResponsDto();
		String charsToRetain = "0123456789"; 
		Long productId = new Long(1);
		try{
			strBase64Encode = new String(Base64Utils.decode((plantCode + "=").getBytes()));
		}catch(IllegalArgumentException e){
			registerResponsDto.setErrorCode("잘못된 코드");
			registerResponsDto.setResult("false");
			return registerResponsDto;
		}
		try {
			productId = Long.parseLong(CharMatcher.anyOf(charsToRetain).retainFrom(strBase64Encode));
		} catch (NumberFormatException e) {
			registerResponsDto.setErrorCode("없는 코드");
			registerResponsDto.setResult("false");
			return registerResponsDto;
		}
		Product product = new Product();
		try {
			product = productRepository.findById(productId).get();
		} catch (NoSuchElementException e) {
			registerResponsDto.setErrorCode("없는 코드");
			registerResponsDto.setResult("false");
			return registerResponsDto;
		}

		if(product.getUsername() != null){
			registerResponsDto.setErrorCode("이미 등록된 코드");
			registerResponsDto.setResult("false");
			return registerResponsDto;
		}
		
		
		product.setUsername(name);
		product.setPlantname(plantname);
		product = productRepository.save(product);
		
		registerResponsDto.setResult("true");
		return registerResponsDto;
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
