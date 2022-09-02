package com.example.fourgrowing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fourgrowing.data.entity.Plant;

public interface PlantRepository extends JpaRepository<Plant, Long>{
    Plant findByPlantType(String plantType);
}
