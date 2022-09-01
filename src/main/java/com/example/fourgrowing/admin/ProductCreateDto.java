package com.example.fourgrowing.admin;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCreateDto {
    @NotEmpty
    private String plantType;
    @NotEmpty
    private String plantCode;
}

