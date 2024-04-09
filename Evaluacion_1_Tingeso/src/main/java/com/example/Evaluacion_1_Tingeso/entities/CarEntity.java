package com.example.Evaluacion_1_Tingeso.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
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

    @Column(name = "car_brand_id")
    private int car_brand_id;

    @Column(name = "model")
    private String model;

    @Column(name = "type")
    private String type;

    @Column(name = "year_of_fabrication")
    private LocalDate year_of_fabrication;

    @Column(name = "motor")
    private int motor;

    @Column(name = "number_of_seats")
    private int number_of_seats;

    @Column(name = "kilometers")
    private int kilometers;
}
