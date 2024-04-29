package com.example.Evaluacion_1_Tingeso.controllers;

import com.example.Evaluacion_1_Tingeso.entities.Report_type_repairEntity;
import com.example.Evaluacion_1_Tingeso.services.Report_type_repairService;
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

@WebMvcTest(Report_type_repairController.class)
public class Report_type_repairControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Report_type_repairService report_type_repairService;

    @Test
    public void listReport_type_repair_ShouldReturnReports_type_repair() throws Exception {
        Report_type_repairEntity reportTypeRepairEntity1 = new Report_type_repairEntity(1L, "Motor", "Sedán",1, 100000);
        Report_type_repairEntity reportTypeRepairEntity2 = new Report_type_repairEntity(2L, "Braking System", "SUV",0,0);

        List<Report_type_repairEntity> report_type_repairEntities = new ArrayList<>(Arrays.asList(reportTypeRepairEntity1, reportTypeRepairEntity2));

        given(report_type_repairService.getAllReport_type_repairs()).willReturn((ArrayList<Report_type_repairEntity>) report_type_repairEntities);

        mockMvc.perform(get("/api/report_type_repair/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)));
    }

    @Test
    public void calculateReport_type_repair_ShouldReturnReports_type_repair() throws Exception {
        Report_type_repairEntity reportTypeRepairEntity1 = new Report_type_repairEntity(1L, "Motor", "Sedán",1, 100000);
        Report_type_repairEntity reportTypeRepairEntity2 = new Report_type_repairEntity(2L, "Braking System", "SUV",0,0);

        List<Report_type_repairEntity> report_type_repairEntities = new ArrayList<>(Arrays.asList(reportTypeRepairEntity1, reportTypeRepairEntity2));

        given(report_type_repairService.getReport_type_repairs()).willReturn((ArrayList<Report_type_repairEntity>) report_type_repairEntities);

        mockMvc.perform(get("/api/report_type_repair/calculate"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)));
    }
}
