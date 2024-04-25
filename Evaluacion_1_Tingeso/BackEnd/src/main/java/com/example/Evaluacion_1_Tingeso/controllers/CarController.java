package com.example.Evaluacion_1_Tingeso.controllers;

import com.example.Evaluacion_1_Tingeso.entities.CarEntity;
import com.example.Evaluacion_1_Tingeso.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@CrossOrigin("*")
public class CarController {
    @Autowired
    CarService carService;

    @GetMapping("/")
    public ResponseEntity<List<CarEntity>> listCars() {
        List<CarEntity> cars = carService.getCars();
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{plate}")
    public ResponseEntity<CarEntity> getCarByPlate(@PathVariable String plate) {
        CarEntity car = carService.getCarByPlate(plate);
        return ResponseEntity.ok(car);
    }

    @GetMapping("/byMotor/{id}")
    public ResponseEntity<List<CarEntity>> getCarByBrandId(@PathVariable Long id) {
        List<CarEntity> cars = carService.getCarByBrandId(id);
        return ResponseEntity.ok(cars);
    }

    @PostMapping("/")
    public ResponseEntity<CarEntity> saveCar(@RequestBody CarEntity car) {
        CarEntity carNew = carService.saveCar(car);
        return ResponseEntity.ok(carNew);
    }

    @PutMapping("/")
    public ResponseEntity<CarEntity> updateCar(@RequestBody CarEntity car) {
        CarEntity carUpdated = carService.updateCar(car);
        return ResponseEntity.ok(carUpdated);
    }

    @DeleteMapping("/{plate}")
    public ResponseEntity<Boolean> deleteCarByPlate(@PathVariable String plate) throws Exception {
        var isDeleted = carService.deleteCar(plate);
        return ResponseEntity.noContent().build();
    }
}
