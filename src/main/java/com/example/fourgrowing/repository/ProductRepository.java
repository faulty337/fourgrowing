package com.example.fourgrowing.repository;

import com.example.fourgrowing.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>{
    
}
