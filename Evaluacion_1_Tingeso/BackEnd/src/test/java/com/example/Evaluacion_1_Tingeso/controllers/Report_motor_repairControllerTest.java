package com.example.Evaluacion_1_Tingeso.controllers;

import com.example.Evaluacion_1_Tingeso.entities.Report_motor_repairEntity;
import com.example.Evaluacion_1_Tingeso.services.Report_motor_repairService;
import org.junit.jupiter.api.Test;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(Report_motor_repairController.class)
public class Report_motor_repairControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Report_motor_repairService report_motor_repairService;

    @Test
    public void listReport_motor_repair_ShouldReturnReports_motor_repair() throws Exception {
        Report_motor_repairEntity reportMotorRepairEntity1 = new Report_motor_repairEntity(1L, 0, "Braking System", 1,10000046);
        Report_motor_repairEntity reportMotorRepairEntity2 = new Report_motor_repairEntity(2L, 0, "Motor", 0,0);

        List<Report_motor_repairEntity> report_motor_repairEntities = new ArrayList<>(Arrays.asList(reportMotorRepairEntity1, reportMotorRepairEntity2));

        given(report_motor_repairService.getAllReport_motor_repairs()).willReturn((ArrayList<Report_motor_repairEntity>) report_motor_repairEntities);

        mockMvc.perform(get("/api/report_motor_repair/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)));
    }

    @Test
    public void calculateReport_motor_repair_ShouldReturnReports_motor_repair() throws Exception {
        Report_motor_repairEntity reportMotorRepairEntity1 = new Report_motor_repairEntity(1L, 0, "Braking System", 1,10000046);
        Report_motor_repairEntity reportMotorRepairEntity2 = new Report_motor_repairEntity(2L, 0, "Motor", 0,0);

        List<Report_motor_repairEntity> report_motor_repairEntities = new ArrayList<>(Arrays.asList(reportMotorRepairEntity1, reportMotorRepairEntity2));

        given(report_motor_repairService.getReport_motor_repairs()).willReturn((ArrayList<Report_motor_repairEntity>) report_motor_repairEntities);

        mockMvc.perform(get("/api/report_motor_repair/calculate"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)));
    }
}
