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
    @Column(name = "receiptId")
    private Long receiptId;

    @Column(name = "workshopInDate")
    private LocalDate workshopInDate;

    @Column(name = "workshopInHour")
    private LocalTime workshopInHour;

    @Column(name = "typeOfRepairId")
    private int typeOfRepairId;

    @Column(name = "totalAmount")
    private int totalAmount;

    @Column(name = "workshopOutDate")
    private LocalDate workshopOutDate;

    @Column(name = "workshopOutHour")
    private LocalTime workshopOutHour;

    @Column(name = "pickUpDate")
    private LocalDate pickUpDate;

    @Column(name = "pickUpHour")
    private LocalTime pickUpHour;

    @Column(name = "carPlate")
    private String carPlate;

    @Column(name = "brandBond")
    private int brandBond;

    @Column(name = "dayOfAttentionDisc")
    private float dayOfAttentionDisc;

    @Column(name = "numberOfRepairsDisc")
    private float numberOfRepairsDisc;

    @Column(name = "ageVehicleSurcharge")
    private float ageVehicleSurcharge;

    @Column(name = "delayOfPickUpSurcharge")
    private float delayOfPickUpSurcharge;

    @Column (name = "kilometersSurcharge")
    private float kilometersSurcharge;

}
