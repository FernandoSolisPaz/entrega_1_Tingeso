package com.example.Evaluacion_1_Tingeso.controllers;

import com.example.Evaluacion_1_Tingeso.entities.RepairsEntity;
import com.example.Evaluacion_1_Tingeso.services.RepairsService;
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

@WebMvcTest(RepairsController.class)
public class RepairControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RepairsService repairsService;

    @Test
    public void listRepairs_ShouldReturnRepairs() throws Exception {
        RepairsEntity repairEntity1 = new RepairsEntity(1L, "motor", 0, 4000);
        RepairsEntity repairEntity2 = new RepairsEntity(2L, "fuel system", 1, 30000);

        List<RepairsEntity> repairEntities = new ArrayList<>(Arrays.asList(repairEntity1, repairEntity2));

        given(repairsService.getRepairs()).willReturn((ArrayList<RepairsEntity>) repairEntities);

        mockMvc.perform(get("/api/repairs/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].repairId", is(1)))
                .andExpect(jsonPath("$[1].repairId", is(2)));
    }

    @Test
    public void getRepairById_ShouldReturnRepair() throws Exception {
        RepairsEntity repair = new RepairsEntity(1L, "motor", 0, 4000);
        given(repairsService.getRepairById(1L)).willReturn(repair);

        mockMvc.perform(get("/api/repairs/{id}",1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.repairId", is(1)))
                .andExpect(jsonPath("$.repairName", is("motor")))
                .andExpect(jsonPath("$.typeOfMotor", is(0)))
                .andExpect(jsonPath("$.costOfRepair", is(4000)));
    }

    @Test
    public void saveRepair_ShouldReturnSavedRepair() throws Exception {
        RepairsEntity savedRepair = new RepairsEntity(1L, "Motor", 0, 4000);
        given(repairsService.saveRepair(Mockito.any(RepairsEntity.class))).willReturn(savedRepair);

        String repairJson = """
                {
                    "repairName" : "Motor",
                    "typeOfMotor" : 0,
                    "costOfRepair" : 4000
                }
                """;
        mockMvc.perform(post("/api/repairs/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(repairJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.repairName", is("Motor")));
    }

    @Test
    public void updateRepair_ShouldReturnUpdatedRepair() throws Exception {
        RepairsEntity updatedRepair = new RepairsEntity(1L, "Motor", 0, 4000);

        given(repairsService.updateRepair(Mockito.any(RepairsEntity.class))).willReturn(updatedRepair);

        String repairJson = """
                {
                    "repairId" : 1,
                    "repairName" : "Motor",
                    "typeOfMotor" : 0,
                    "costOfRepair" : 4000
                }
                """;

        mockMvc.perform(put("/api/repairs/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(repairJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.repairName", is("Motor")));
    }
}
