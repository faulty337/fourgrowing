package com.example.fourgrowing.data.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlantResponseDto {
    private Long id;
    private String plantType;
    private int changeWater;
    private int givingWater;
}
