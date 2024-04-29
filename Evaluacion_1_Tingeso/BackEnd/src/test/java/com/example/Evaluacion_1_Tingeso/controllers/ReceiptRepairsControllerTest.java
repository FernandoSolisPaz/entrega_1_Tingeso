package com.example.Evaluacion_1_Tingeso.controllers;

import com.example.Evaluacion_1_Tingeso.entities.ReceiptRepairsEntity;
import com.example.Evaluacion_1_Tingeso.services.ReceiptRepairsService;
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

@WebMvcTest(ReceiptRepairsController.class)
public class ReceiptRepairsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReceiptRepairsService receiptRepairsService;

    @Test
    public void listReceiptRepairs_ShouldReturnReceiptRepairs() throws Exception {
        ReceiptRepairsEntity receiptRepairsEntity1 = new ReceiptRepairsEntity(1L, 1L, 1L);
        ReceiptRepairsEntity receiptRepairsEntity2 = new ReceiptRepairsEntity(2L, 1L, 2L);

        List<ReceiptRepairsEntity> receiptRepairsEntities = new ArrayList<>(Arrays.asList(receiptRepairsEntity1, receiptRepairsEntity2));

        given(receiptRepairsService.getReceiptRepairs()).willReturn((ArrayList<ReceiptRepairsEntity>) receiptRepairsEntities);

        mockMvc.perform(get("/api/receiptsRepairs/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].receiptRepairsId", is(1)))
                .andExpect(jsonPath("$[1].receiptRepairsId", is(2)));
    }

    @Test
    public void getCarByPlate_ShouldReturnCar() throws Exception {
        ReceiptRepairsEntity receiptRepairsEntity1 = new ReceiptRepairsEntity(1L, 1L, 1L);
        ReceiptRepairsEntity receiptRepairsEntity2 = new ReceiptRepairsEntity(2L, 1L, 2L);

        List<ReceiptRepairsEntity> receiptRepairsEntities = new ArrayList<>(Arrays.asList(receiptRepairsEntity1, receiptRepairsEntity2));
        given(receiptRepairsService.getByReceiptId(1L)).willReturn(receiptRepairsEntities);

        mockMvc.perform(get("/api/receiptsRepairs/byReceipt/{id}",1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].receiptId", is(1)))
                .andExpect(jsonPath("$[1].receiptId", is(1)));
    }

    @Test
    public void saveReceiptRepairs_ShouldReturnSavedReceiptRepairs() throws Exception {
        ReceiptRepairsEntity savedReceiptRepairs = new ReceiptRepairsEntity(1L, 1L, 1L);
        given(receiptRepairsService.saveReceiptRepairs(Mockito.any(ReceiptRepairsEntity.class))).willReturn(savedReceiptRepairs);

        String receiptRepairsJson = """
                {
                    "receiptId": 1,
                    "repairId": 1
                }
                """;
        mockMvc.perform(post("/api/receiptsRepairs/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(receiptRepairsJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.receiptId", is(1)))
                .andExpect(jsonPath("$.receiptId", is(1)));
    }

    @Test
    public void updateReceiptRepairs_ShouldReturnUpdatedReceiptRepairs() throws Exception {
        ReceiptRepairsEntity updatedReceiptRepair = new ReceiptRepairsEntity(1L, 1L, 1L);

        given(receiptRepairsService.updateReceiptRepairs(Mockito.any(ReceiptRepairsEntity.class))).willReturn(updatedReceiptRepair);

        String receiptRepairJson = """
                {
                    "receiptRepairId": 1,
                    "receiptId": 1,
                    "repairId": 1
                }
                """;

        mockMvc.perform(put("/api/receiptsRepairs/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(receiptRepairJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.receiptId", is(1)));
    }

    @Test
    public void deleteReceiptRepairsById_ShouldReturn204() throws Exception {
        when(receiptRepairsService.deleteReceiptRepairs(1L)).thenReturn(true);
        mockMvc.perform(delete("/api/receiptsRepairs/{id}",1L))
                .andExpect(status().isNoContent());
    }
}
