package com.example.Evaluacion_1_Tingeso.repositories;

import com.example.Evaluacion_1_Tingeso.entities.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, String> {
    ArrayList<CarEntity> findByCarBrandId(Long carBrandId);

}
