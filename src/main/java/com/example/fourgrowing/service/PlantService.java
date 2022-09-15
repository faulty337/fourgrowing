package com.example.fourgrowing.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fourgrowing.data.dto.PlantCreateDto;
import com.example.fourgrowing.data.dto.PlantResponseDto;
import com.example.fourgrowing.data.entity.Plant;
import com.example.fourgrowing.repository.PlantRepository;

@Service
public class PlantService {

    @Autowired
    PlantRepository plantRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public List<PlantResponseDto> getPlantList(){
    
        return plantRepository.findAll().stream().map(plant -> modelMapper.map(plant, PlantResponseDto.class)).collect(Collectors.toList());
    }

    public void createPlant(PlantCreateDto plantCreateDto) {
        Plant plant = new Plant();
        plant.setPlantType(plantCreateDto.getPlantType());
        plant.setChangeWater(plantCreateDto.getChangeWater());
        plant.setGivingWater(plantCreateDto.getGivingWater());
        plantRepository.save(plant);
    }

}
