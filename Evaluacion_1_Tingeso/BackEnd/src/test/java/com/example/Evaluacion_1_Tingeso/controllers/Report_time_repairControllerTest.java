package com.example.Evaluacion_1_Tingeso.controllers;

import com.example.Evaluacion_1_Tingeso.entities.ReceiptEntity;
import com.example.Evaluacion_1_Tingeso.entities.Report_time_repairEntity;
import com.example.Evaluacion_1_Tingeso.services.Report_time_repairService;
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

@WebMvcTest(Report_time_repairController.class)
public class Report_time_repairControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Report_time_repairService report_time_repairService;

    @Test
    public void listReport_time_repair_ShouldReturnReports_time_repair() throws Exception {
        Report_time_repairEntity reportTimeRepairEntity1 = new Report_time_repairEntity(1L, "Toyota", 1, 300L,5);
        Report_time_repairEntity reportTimeRepairEntity2 = new Report_time_repairEntity(2L, "Nissan", 1, 240L,4);

        List<Report_time_repairEntity> report_time_repairEntities = new ArrayList<>(Arrays.asList(reportTimeRepairEntity1, reportTimeRepairEntity2));

        given(report_time_repairService.getAllReport_time_repairs()).willReturn((ArrayList<Report_time_repairEntity>) report_time_repairEntities);

        mockMvc.perform(get("/api/report_time_repair/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)));
    }

    @Test
    public void calculateReport_time_repair_ShouldReturnReports_time_repair() throws Exception {
        Report_time_repairEntity reportTimeRepairEntity1 = new Report_time_repairEntity(1L, "Toyota", 1, 300L,5);
        Report_time_repairEntity reportTimeRepairEntity2 = new Report_time_repairEntity(2L, "Nissan", 1, 240L,4);

        List<Report_time_repairEntity> report_time_repairEntities = new ArrayList<>(Arrays.asList(reportTimeRepairEntity1, reportTimeRepairEntity2));

        given(report_time_repairService.getReport_time_repairs()).willReturn((ArrayList<Report_time_repairEntity>) report_time_repairEntities);

        mockMvc.perform(get("/api/report_time_repair/calculate"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)));
    }
}
