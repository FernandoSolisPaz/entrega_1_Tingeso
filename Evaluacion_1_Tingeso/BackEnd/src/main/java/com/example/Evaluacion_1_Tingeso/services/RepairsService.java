package com.example.Evaluacion_1_Tingeso.services;

import com.example.Evaluacion_1_Tingeso.entities.RepairsEntity;
import com.example.Evaluacion_1_Tingeso.repositories.RepairsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepairsService {

    @Autowired
    RepairsRepository repairsRepository;

    public List<RepairsEntity> getRepairs(){ return repairsRepository.findAll(); }

    public RepairsEntity saveRepair(RepairsEntity repair){return repairsRepository.save(repair); }

    public RepairsEntity getRepairById(Long id) { return repairsRepository.findById(id).get(); }

    public RepairsEntity updateRepair(RepairsEntity carBrand){ return repairsRepository.save(carBrand); }

    public RepairsEntity getByMotorIdAndRepairName(int id, String name) { return repairsRepository.findByMotorAndRepairName(id, name); }
}
