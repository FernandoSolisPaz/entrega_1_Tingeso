package com.example.Evaluacion_1_Tingeso.repositories;

import com.example.Evaluacion_1_Tingeso.entities.Report_type_repairEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Report_type_repairRepository extends JpaRepository<Report_type_repairEntity, Long> {

    @Query(value = "SELECT * FROM Report_type_repair WHERE Report_type_repair.repair_name = :repairName AND Report_type_repair.type_name = :type", nativeQuery = true)
    public Report_type_repairEntity findByTypeAndRepairName(@Param("type") String type, @Param("repairName") String repairName);

    @Query(value = "SELECT * FROM Report_type_repair ORDER BY Report_type_repair.total_amount DESC", nativeQuery = true)
    List<Report_type_repairEntity> findAllByOrderByAmount();
}
