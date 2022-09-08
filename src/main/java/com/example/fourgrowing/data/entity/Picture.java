/*
package com.example.fourgrowing.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String userName;

    @Column(length = 500, nullable = false)
    private String originName;
    */
/*업로드한 기존 파일명*//*


    @Column(length = 500, nullable = false)
    private String storeName;
    */
/*실제 저장되는 파일명*//*

    */
/*이름이 겹치는 것을 방지하기 위해 새로 이름을 부여*//*


    @Column(length = 1000, nullable = false)
    private String path;

    private String use;
    */
/*사진을 사용하는 용도*//*


    private String size;
}
*/
