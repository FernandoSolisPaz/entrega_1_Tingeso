package com.example.Evaluacion_1_Tingeso.services;

import com.example.Evaluacion_1_Tingeso.entities.CarEntity;
import com.example.Evaluacion_1_Tingeso.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CarService {
    @Autowired
    CarRepository carRepository;

    public ArrayList<CarEntity> getCars(){ return (ArrayList<CarEntity>) carRepository.findAll(); }

    public CarEntity saveCar(CarEntity car){return carRepository.save(car); }

    public CarEntity getCarByPlate(String plate) { return carRepository.findById(plate).get(); }

    public ArrayList<CarEntity> getCarByBrandId(Long id){ return carRepository.findByCarBrandId(id); }

    public CarEntity updateCar(CarEntity carBrand){ return carRepository.save(carBrand); }

    public boolean deleteCar(String plate) throws Exception{
        try{
            carRepository.deleteById(plate);
            return true;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
