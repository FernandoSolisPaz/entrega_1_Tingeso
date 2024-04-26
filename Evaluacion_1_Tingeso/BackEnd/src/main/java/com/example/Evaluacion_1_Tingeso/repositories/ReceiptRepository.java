package com.example.Evaluacion_1_Tingeso.repositories;

import com.example.Evaluacion_1_Tingeso.entities.ReceiptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceiptRepository extends JpaRepository<ReceiptEntity, Long> {

    List<ReceiptEntity> findByCarPlate(String carPlate);

    @Query(value = "SELECT COUNT(re) FROM ReceiptEntity re WHERE re.carPlate = :car_plate AND re.workshopInDate >= CURRENT_DATE - INTERVAL '12 months'", nativeQuery = true)
    int countReceiptEntitiesByNumberOfRepairsIn12Months(@Param("car_plate") String car_plate);

}
