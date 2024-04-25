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
    @Column(name = "plate")
    private String plate;

    @Column(name = "carBrandId")
    private Long carBrandId;

    @Column(name = "model")
    private String model;

    /*
    * Sedan = 0
    * Hatchback = 1
    * SUV = 2
    * Pickup = 3
    * Furgoneta = 4
    * */
    @Column(name = "type")
    private int type;

    @Column(name = "yearOfFabrication")
    private LocalDate yearOfFabrication;

    /*
    * Gasolina = 0
    * Diésel = 1
    * Híbrido = 2
    * Eléctrico = 3
    * */
    @Column(name = "motor")
    private int motor;

    @Column(name = "numberOfSeats")
    private int numberOfSeats;

    @Column(name = "kilometers")
    private int kilometers;
}
