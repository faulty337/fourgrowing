package com.example.fourgrowing.data.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDto {
    private Long id;
    private String userName;
    private String plantType;
    private String plantCode;
}