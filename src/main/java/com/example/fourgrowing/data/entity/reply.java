package com.example.fourgrowing.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    private String boardId;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;
}
