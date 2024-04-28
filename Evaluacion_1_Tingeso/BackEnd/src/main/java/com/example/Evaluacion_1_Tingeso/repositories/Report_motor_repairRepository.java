package com.example.Evaluacion_1_Tingeso.repositories;

import com.example.Evaluacion_1_Tingeso.entities.Report_motor_repairEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Report_motor_repairRepository extends JpaRepository<Report_motor_repairEntity, Long> {

    @Query(value = "SELECT * FROM Report_motor_repair WHERE Report_motor_repair.repair_name = :repairName AND Report_motor_repair.motor_id = :motorId", nativeQuery = true)
    public Report_motor_repairEntity findByMotorAndRepairName(@Param("motorId") int motorId, @Param("repairName") String repairName);

    @Query(value = "SELECT * FROM Report_motor_repair ORDER BY Report_motor_repair.total_amount DESC", nativeQuery = true)
    List<Report_motor_repairEntity> findAllByOrderByAmount();
}
