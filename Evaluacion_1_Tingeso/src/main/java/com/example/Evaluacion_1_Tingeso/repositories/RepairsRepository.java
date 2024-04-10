package com.example.Evaluacion_1_Tingeso.repositories;

import com.example.Evaluacion_1_Tingeso.entities.RepairsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepairsRepository extends JpaRepository<RepairsEntity, Long>{
    List<RepairsEntity> findByTypeOfMotor(int typeOfMotorId);
    public RepairsEntity findByRepairName(String brand_name);
}
