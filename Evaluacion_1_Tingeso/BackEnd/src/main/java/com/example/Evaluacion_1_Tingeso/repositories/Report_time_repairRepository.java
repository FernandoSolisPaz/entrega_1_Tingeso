package com.example.Evaluacion_1_Tingeso.repositories;

import com.example.Evaluacion_1_Tingeso.entities.Report_time_repairEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Report_time_repairRepository extends JpaRepository<Report_time_repairEntity, Long> {
    @Query(value = "SELECT * FROM Report_time_repair WHERE Report_time_repair.brand_name = :brandName", nativeQuery = true)
    public Report_time_repairEntity findByBrandName(@Param("brandName") String brandName);

    @Query(value = "SELECT * FROM Report_time_repair ORDER BY Report_time_repair.avg_hours DESC", nativeQuery = true)
    List<Report_time_repairEntity> findAllByOrderByHours();
}
