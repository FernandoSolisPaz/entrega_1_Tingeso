package com.example.Evaluacion_1_Tingeso.services;

import com.example.Evaluacion_1_Tingeso.entities.Car_brandEntity;
import com.example.Evaluacion_1_Tingeso.repositories.Car_brandRepository;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@WebMvcTest(Car_brandService.class)
public class Car_brandServiceTest {

    @Autowired
    private Car_brandService car_brandService;

    @MockBean
    private Car_brandRepository car_brandRepository;

    @Test
    void whenSaveCarBrand_thenCorrect() {
        Car_brandEntity car_brandEntity1 = new Car_brandEntity(null, "Toyota", 5,40000);
        when(car_brandRepository.save(Mockito.any(Car_brandEntity.class))).thenReturn(car_brandEntity1);

        Car_brandEntity result = car_brandService.saveCarBrand(car_brandEntity1);
        assertEquals(car_brandEntity1, result);
    }

    @Test
    void whenGetCarBrands_thenCorrect() {
        Car_brandEntity car_brandEntity1 = new Car_brandEntity();
        Car_brandEntity car_brandEntity2 = new Car_brandEntity();
        List<Car_brandEntity> car_brandEntities = new ArrayList<>(Arrays.asList(car_brandEntity1, car_brandEntity2));
        when(car_brandRepository.findAll()).thenReturn(car_brandEntities);
        List<Car_brandEntity> result = car_brandService.getCarBrand();
        assertEquals(2, result.size());
    }


    @Test
    void whenGetCarBrandById_thenCorrect() {
        Car_brandEntity car_brandEntity1 = new Car_brandEntity(1L, "Toyota", 5,40000);
        when(car_brandRepository.findById(1L)).thenReturn(Optional.ofNullable(car_brandEntity1));

        Car_brandEntity result = car_brandService.getCarBrandById(1L);
        assertEquals(car_brandEntity1, result);
    }

    @Test
    void whenUpdateCar_thenCorrect() {
        Car_brandEntity car_brandEntity1 = new Car_brandEntity(1L, "Toyota", 5,40000);
        when(car_brandRepository.save(car_brandEntity1)).thenReturn(car_brandEntity1);
        Car_brandEntity updatedCarBrand = car_brandService.updateCarBrand(car_brandEntity1);
        assertEquals(car_brandEntity1, updatedCarBrand);
    }

    @Test
    void whenDeleteCarBrand_thenCorrect() {
        Car_brandEntity car_brandEntity1 = new Car_brandEntity(1L, "Toyota", 5,40000);

        when(car_brandRepository.save(Mockito.any(Car_brandEntity.class))).thenReturn(car_brandEntity1);
        boolean result = false;
        try {
            result = car_brandService.deleteCarBrand(1L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Then
        assertThat(result).isTrue();
    }

    @Test
    void whenDeleteCarBrandE_thenCorrect() {

        doThrow(new RuntimeException("java.lang.RuntimeException")).when(car_brandRepository).deleteById(anyLong());

        Exception exception = assertThrows(Exception.class, () -> car_brandService.deleteCarBrand(1L));

        assertEquals("java.lang.RuntimeException", exception.getMessage());
    }
}
