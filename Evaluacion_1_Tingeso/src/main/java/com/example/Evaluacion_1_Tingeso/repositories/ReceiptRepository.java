package com.example.Evaluacion_1_Tingeso.repositories;

import com.example.Evaluacion_1_Tingeso.entities.ReceiptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ReceiptRepository extends JpaRepository<ReceiptEntity, Long> {

    ArrayList<ReceiptEntity> findByCarPlate(String carPlate);


}
