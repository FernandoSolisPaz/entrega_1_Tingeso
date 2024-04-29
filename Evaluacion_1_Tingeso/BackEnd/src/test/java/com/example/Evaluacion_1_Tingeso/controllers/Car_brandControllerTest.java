package com.example.Evaluacion_1_Tingeso.controllers;


import com.example.Evaluacion_1_Tingeso.entities.Car_brandEntity;
import com.example.Evaluacion_1_Tingeso.services.Car_brandService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(Car_brandController.class)
public class Car_brandControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Car_brandService car_brandService;

    @Test
    public void listCar_brands_ShouldReturnCarBrands() throws Exception {
        Car_brandEntity car_brandEntity1 = new Car_brandEntity(1L, "Toyota", 5,40000);
        Car_brandEntity car_brandEntity2 = new Car_brandEntity(2L, "Jeep", 3,30000);

        List<Car_brandEntity> car_brandEntities = new ArrayList<>(Arrays.asList(car_brandEntity1, car_brandEntity2));

        given(car_brandService.getCarBrand()).willReturn((ArrayList<Car_brandEntity>) car_brandEntities);

        mockMvc.perform(get("/api/car_brand/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)));
    }

    @Test
    public void getCarBrandById_ShouldReturnCarBrand() throws Exception {
        Car_brandEntity car_brand = new Car_brandEntity(1L, "Toyota", 5,40000);
        given(car_brandService.getCarBrandById(1L)).willReturn(car_brand);

        mockMvc.perform(get("/api/car_brand/{id}",1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.brandName", is("Toyota")))
                .andExpect(jsonPath("$.bondAvailable", is(5)))
                .andExpect(jsonPath("$.amount", is(40000)));
    }

    @Test
    public void saveCarBrand_ShouldReturnSavedCarBrand() throws Exception {
        Car_brandEntity savedCarBrand = new Car_brandEntity(1L, "Toyota", 5,40000);
        given(car_brandService.saveCarBrand(Mockito.any(Car_brandEntity.class))).willReturn(savedCarBrand);

        String car_brandJson = """
                {
                    "brandName" : "Toyota",
                    "bondAvailable" : 5,
                    "amount" : 40000
                }
                """;
        mockMvc.perform(post("/api/car_brand/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(car_brandJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brandName", is("Toyota")));
    }

    @Test
    public void updateCarBrand_ShouldReturnUpdatedCarBrand() throws Exception {
        Car_brandEntity updatedCarBrand = new Car_brandEntity(1L, "Toyota", 5,40000);

        given(car_brandService.updateCarBrand(Mockito.any(Car_brandEntity.class))).willReturn(updatedCarBrand);

        String carJson = """
                {
                    "id" : 1,
                    "brandName" : "Toyota",
                    "bondAvailable" : 5,
                    "amount" : 40000
                }
                """;

        mockMvc.perform(put("/api/car_brand/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(carJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    public void deleteCarBrandById_ShouldReturn204() throws Exception {
        when(car_brandService.deleteCarBrand(1L)).thenReturn(true);
        mockMvc.perform(delete("/api/car_brand/{id}",1))
                .andExpect(status().isNoContent());
    }
}
