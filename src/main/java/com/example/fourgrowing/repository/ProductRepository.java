package com.example.fourgrowing.repository;

import com.example.fourgrowing.data.entity.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>{
    Product findByUsernameAndPlantname(String Username, String Plantname);
    List<Product> findByUsername(String UserName);
    Product findByPlantCode(String ProductCode);
}
