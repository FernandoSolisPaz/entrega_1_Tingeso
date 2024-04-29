package com.example.Evaluacion_1_Tingeso.controllers;

import com.example.Evaluacion_1_Tingeso.entities.CarEntity;
import com.example.Evaluacion_1_Tingeso.services.CarService;
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

@WebMvcTest(CarController.class)
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService carService;

    @Test
    public void listCars_ShouldReturnCars() throws Exception {
        CarEntity carEntity1 = new CarEntity("CGZG94", 1L, "4x4", 1, 2023, 0, 4, 4000);
        CarEntity carEntity2 = new CarEntity("BYES11", 2L, "Corolla", 1, 2003, 0, 4, 30000);

        List<CarEntity> carEntities = new ArrayList<>(Arrays.asList(carEntity1, carEntity2));

        given(carService.getCars()).willReturn((ArrayList<CarEntity>) carEntities);

        mockMvc.perform(get("/api/cars/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].plate", is("CGZG94")))
                .andExpect(jsonPath("$[1].plate", is("BYES11")));
    }

    @Test
    public void getCarByPlate_ShouldReturnCar() throws Exception {
        CarEntity car = new CarEntity("CGZG94", 1L, "4x4", 1, 2023, 0, 4, 4000);
        given(carService.getCarByPlate("CGZG94")).willReturn(car);

        mockMvc.perform(get("/api/cars/{plate}","CGZG94"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.carBrandId", is(1)))
                .andExpect(jsonPath("$.model", is("4x4")))
                .andExpect(jsonPath("$.type", is(1)))
                .andExpect(jsonPath("$.yearOfFabrication", is(2023)));
    }

    @Test
    public void saveCar_ShouldReturnSavedCar() throws Exception {
        CarEntity savedCar = new CarEntity("CGZG94", 1L, "4x4", 1, 2023, 0, 4, 4000);
        given(carService.saveCar(Mockito.any(CarEntity.class))).willReturn(savedCar);

        String carJson = """
                {
                    "plate": "CGZG94",
                    "carBrandId": 1,
                    "model": "4x4",
                    "type": 1,
                    "yearOfFabrication": 2023,
                    "motor": 0,
                    "numberOfSeats": 4,
                    "kilometers": 4000
                }
                """;
        mockMvc.perform(post("/api/cars/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(carJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.carBrandId", is(1)));
    }

    @Test
    public void updateCar_ShouldReturnUpdatedCar() throws Exception {
        CarEntity updatedCar = new CarEntity("CGZG94", 1L, "4x4", 1, 2023, 0, 4, 4000);

        given(carService.updateCar(Mockito.any(CarEntity.class))).willReturn(updatedCar);

        String carJson = """
                {
                    "plate": "CGZG94",
                    "carBrandId": 1,
                    "model": "4x4",
                    "type": 1,
                    "yearOfFabrication": 2023,
                    "motor": 0,
                    "numberOfSeats": 4,
                    "kilometers": 4000
                }
                """;

        mockMvc.perform(put("/api/cars/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(carJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.carBrandId", is(1)));
    }

    @Test
    public void deleteCarById_ShouldReturn204() throws Exception {
        when(carService.deleteCar("CGZG94")).thenReturn(true);
        mockMvc.perform(delete("/api/cars/{plate}","CGZG94"))
                .andExpect(status().isNoContent());
    }
}
