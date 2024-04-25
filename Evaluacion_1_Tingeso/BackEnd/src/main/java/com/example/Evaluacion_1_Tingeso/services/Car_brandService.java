package com.example.Evaluacion_1_Tingeso.services;


import com.example.Evaluacion_1_Tingeso.entities.Car_brandEntity;
import com.example.Evaluacion_1_Tingeso.repositories.Car_brandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Car_brandService {
    @Autowired
    Car_brandRepository car_brandRepository;

    public List<Car_brandEntity> getCarBrand(){ return car_brandRepository.findAll(); }

    public Car_brandEntity saveCarBrand(Car_brandEntity carBrand){return car_brandRepository.save(carBrand); }

    public Car_brandEntity getCarBrandByid(Long id) {
        Optional<Car_brandEntity> Car_brandOptional = car_brandRepository.findById(id);
        if(Car_brandOptional.isEmpty()) throw new RuntimeException("La CarBrand " + id + " no existe. ");
        return Car_brandOptional.get(); }
    public Car_brandEntity getCarBrandByName(String name){ return car_brandRepository.findByBrandName(name); }

    public Car_brandEntity updateCarBrand(Car_brandEntity carBrand){ return car_brandRepository.save(carBrand); }

    public boolean deleteCarBrand(Long id) throws Exception{
        try{
            car_brandRepository.deleteById(id);
            return true;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
