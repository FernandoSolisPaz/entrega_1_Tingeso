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
    @Column(name = "brand", unique = true, nullable = false)
    private String brand;

    @Column(name = "bond_available")
    private int bond_available;

    @Column(name = "amount")
    private int amount;

    @OneToMany(mappedBy =  "brand", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<CarEntity> cars;
}
