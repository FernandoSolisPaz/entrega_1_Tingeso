package com.example.Evaluacion_1_Tingeso.services;

import com.example.Evaluacion_1_Tingeso.entities.RepairsEntity;
import com.example.Evaluacion_1_Tingeso.repositories.RepairsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RepairsService {

    @Autowired
    RepairsRepository repairsRepository;

    public ArrayList<RepairsEntity> getRepairs(){ return (ArrayList<RepairsEntity>) repairsRepository.findAll(); }

    public RepairsEntity saveRepair(RepairsEntity repair){return repairsRepository.save(repair); }

    public RepairsEntity getRepairById(Long id) { return repairsRepository.findById(id).get(); }

    public ArrayList<RepairsEntity> getRepairByMotorId(int id){ return repairsRepository.findByTypeOfMotor(id); }

    public RepairsEntity updateRepair(RepairsEntity carBrand){ return repairsRepository.save(carBrand); }

    public boolean deleteRepair(Long id) throws Exception{
        try{
            repairsRepository.deleteById(id);
            return true;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
