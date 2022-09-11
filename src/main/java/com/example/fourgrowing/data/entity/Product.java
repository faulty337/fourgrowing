package com.example.fourgrowing.data.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String plantname;

    private String plantType;

    private LocalDate plantingDay;

    @Column(unique = true)
    private String plantCode;


    @Builder
    public Product(Long id, String plantCode, String plantType){
        this.id = id;
        this.plantCode = plantCode;
        this.plantType = plantType;
        
    }
}
