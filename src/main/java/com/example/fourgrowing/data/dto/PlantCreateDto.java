package com.example.fourgrowing.data.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlantCreateDto {
    private String plantType;
    private int changeWater;
    private int givingWater;
}
