package com.example.Evaluacion_1_Tingeso.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "report_time_repair")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report_time_repairEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "brandName")
    private String brandName;

    @Column(name = "numberOfRepairs")
    private int numberOfRepairs;

    @Column(name = "timeSum")
    private Long timeSum;

    @Column(name = "avgHours")
    private double avgHours;

}
