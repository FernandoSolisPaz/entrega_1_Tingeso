package com.example.Evaluacion_1_Tingeso.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "report_type_repair")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report_type_repairEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "repairName")
    private String repairName;

    @Column(name = "typeName")
    private String typeName;

    @Column(name = "numberOfRepairs")
    private int numberOfRepairs;

    @Column(name = "totalAmount")
    private int totalAmount;

}
