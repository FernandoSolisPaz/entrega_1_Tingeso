package com.example.Evaluacion_1_Tingeso.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;
import jakarta.persistence.*;

@Entity
@Table(name = "receipt")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "receipt_id")
    private int receipt_id;

    @Column(name = "workshop_in_date")
    private LocalDate workshop_in_date;

    @Column(name = "workshop_in_hour")
    private LocalTime workshop_in_hour;

    @Column(name = "type_of_repair_id")
    private int type_of_repair_id;

    @Column(name = "total_amount")
    private int total_mount;

    @Column(name = "workshop_out_date")
    private LocalDate workshop_out_date;

    @Column(name = "workshop_out_hour")
    private LocalTime workshop_out_hour;

    @Column(name = "pick_up_date")
    private LocalDate pick_up_date;

    @Column(name = "pick_up_hour")
    private LocalTime pick_up_hour;

    @Column(name = "car_patent")
    private String car_patent;

    @Column(name = "brand_bond")
    private int brand_bond;

    @Column(name = "day_of_attention_disc")
    private float day_of_attention_disc;

    @Column(name = "number_of_repairs_disc")
    private float number_of_repairs_disc;

    @Column(name = "age_vehicle_surcharge")
    private float age_vehicle_surcharge;

    @Column(name = "delay_of_pick_up_surcharge")
    private float delay_of_pick_up_surcharge;

    @Column (name = "kilometers_surcharge")
    private float kilometers_surcharge;

}
