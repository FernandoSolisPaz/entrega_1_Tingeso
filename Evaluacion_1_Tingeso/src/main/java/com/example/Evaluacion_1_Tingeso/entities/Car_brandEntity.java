package com.example.Evaluacion_1_Tingeso.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

import jakarta.persistence.*;

 @Entity
 @Table(name = "car_brand")
 @Data
 @NoArgsConstructor
 @AllArgsConstructor
public class Car_brandEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    private int brand_id;

    @Column(name = "brand_name")
    private String brand_name;

    @Column(name = "bond_available")
    private int bond_available;

    @Column(name = "amount")
    private int amount;

}
