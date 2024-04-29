package com.example.Evaluacion_1_Tingeso.services;

import com.example.Evaluacion_1_Tingeso.entities.CarEntity;
import com.example.Evaluacion_1_Tingeso.repositories.CarRepository;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.Optional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@WebMvcTest(CarService.class)
public class CarServiceTest {
    @Autowired
    private CarService carService;

    @MockBean
    private CarRepository carRepository;

    @Test
    void whenSaveCar_thenCorrect() {
        CarEntity carEntity1 = new CarEntity("CGZG94", 1L, "4x4", 1, 2023, 0, 4, 4000);
        when(carRepository.save(Mockito.any(CarEntity.class))).thenReturn(carEntity1);

        CarEntity result = carService.saveCar(carEntity1);
        assertEquals(carEntity1, result);
    }

    @Test
    void whenGetCars_thenCorrect() {
        CarEntity carEntity1 = new CarEntity();
        CarEntity carEntity2 = new CarEntity();
        List<CarEntity> carEntities = new ArrayList<>(Arrays.asList(carEntity1, carEntity2));
        when(carRepository.findAll()).thenReturn(carEntities);
        List<CarEntity> result = carService.getCars();
        assertEquals(2, result.size());
    }


    @Test
    void whenGetCarByPlate_thenCorrect() {
        CarEntity carEntity1 = new CarEntity("CGZG94", 1L, "4x4", 1, 2023, 0, 4, 4000);
        when(carRepository.findById("CGZG94")).thenReturn(Optional.ofNullable(carEntity1));

        CarEntity result = carService.getCarByPlate("CGZG94");
        assertEquals(carEntity1, result);
    }

    @Test
    void whenUpdateCar_thenCorrect() {
        CarEntity carEntity1 = new CarEntity("CGZG94", 1L, "4x4", 1, 2023, 0, 4, 4000);
        when(carRepository.save(carEntity1)).thenReturn(carEntity1);
        CarEntity updatedCar = carService.updateCar(carEntity1);
        assertEquals(carEntity1, updatedCar);
    }

    @Test
    void whenDeleteCar_thenCorrect() {
        CarEntity carEntity1 = new CarEntity("CGZG94", 1L, "4x4", 1, 2023, 0, 4, 4000);

        when(carRepository.save(Mockito.any(CarEntity.class))).thenReturn(carEntity1);
        boolean result = false;
        try {
            result = carService.deleteCar("CGZG94");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Then
        assertThat(result).isTrue();
    }

    @Test
    void whenDeleteCarE_thenCorrect() {

        doThrow(new RuntimeException("java.lang.RuntimeException")).when(carRepository).deleteById(anyString());

        Exception exception = assertThrows(Exception.class, () -> carService.deleteCar("CGZG94"));

        assertEquals("java.lang.RuntimeException", exception.getMessage());
    }
}
