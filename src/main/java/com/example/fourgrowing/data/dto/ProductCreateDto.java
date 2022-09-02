package com.example.fourgrowing.data.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCreateDto {
    @NotEmpty
    private String planttype;
    @NotEmpty
    private String plantcode;
}

