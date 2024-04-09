package com.example.Evaluacion_1_Tingeso.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "car")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarEntity {
    @Id
    @Column(name = "patent")
    private String patent;

    @ManyToOne
    @JoinColumn(name = "brand_name", referencedColumnName = "brand")
    @JsonManagedReference
    private Car_brandEntity car_brand;

    @Column(name = "model")
    private String model;

    @Column(name = "type")
    private String type;

    @Column(name = "year_of_fabrication")
    private Date year_of_fabrication;

    @Column(name = "motor")
    private int motor;

    @Column(name = "number_of_seats")
    private int number_of_seats;

    @Column(name = "kilometers")
    private int kilometers;
}
