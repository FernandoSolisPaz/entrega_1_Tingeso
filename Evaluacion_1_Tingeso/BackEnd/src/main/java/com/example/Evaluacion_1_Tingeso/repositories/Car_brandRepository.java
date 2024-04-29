package com.example.Evaluacion_1_Tingeso.repositories;

import com.example.Evaluacion_1_Tingeso.entities.Car_brandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Car_brandRepository extends JpaRepository<Car_brandEntity, Long>{

}
