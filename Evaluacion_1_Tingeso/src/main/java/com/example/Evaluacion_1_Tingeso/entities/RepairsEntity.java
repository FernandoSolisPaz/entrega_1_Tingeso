package com.example.Evaluacion_1_Tingeso.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "repairs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepairsEntity {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "repair_name")
    private String repair_name;

    @Column(name = "type_of_motor")
    private int type_of_motor;

    @Column(name = "cost_of_repair")
    private int cost_of_repair;
}
