package com.example.Evaluacion_1_Tingeso.entities;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import jakarta.persistence.*;

 @Entity
 @Table(name = "car_brand")
 @Data
 @NoArgsConstructor
 @AllArgsConstructor
public class Car_brandEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brandId")
    private Long brandId;

    @Column(name = "brandName")
    private String brandName;

    @Column(name = "bondAvailable")
    private int bondAvailable;

    @Column(name = "amount")
    private int amount;

}
