package com.example.Evaluacion_1_Tingeso.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "report_motor_repair")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report_motor_repairEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "motorId")
    private int motorId;

    @Column(name = "repairName")
    private String repairName;

    @Column(name = "numberOfRepairs")
    private int numberOfRepairs;

    @Column(name = "totalAmount")
    private int totalAmount;
}
