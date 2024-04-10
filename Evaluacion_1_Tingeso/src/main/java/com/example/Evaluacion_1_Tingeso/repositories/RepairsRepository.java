package com.example.Evaluacion_1_Tingeso.repositories;

import com.example.Evaluacion_1_Tingeso.entities.RepairsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface RepairsRepository extends JpaRepository<RepairsEntity, Long>{
    ArrayList<RepairsEntity> findByTypeOfMotor(int typeOfMotorId);
}
