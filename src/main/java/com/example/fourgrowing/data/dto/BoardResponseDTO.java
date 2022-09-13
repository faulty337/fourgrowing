package com.example.fourgrowing.data.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BoardResponseDTO {
    private Long id;
    private String username;
    private String title;
    private String content;
    private LocalDateTime createDate;
}
