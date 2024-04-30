package com.example.Evaluacion_1_Tingeso.controllers;

import com.example.Evaluacion_1_Tingeso.services.ReceiptService;
import com.example.Evaluacion_1_Tingeso.entities.ReceiptEntity;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReceiptController.class)
public class ReceiptControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReceiptService receiptService;

    @Test
    public void listReceipts_ShouldReturnReceipts() throws Exception {
        ReceiptEntity receiptEntity1 = new ReceiptEntity(1L, null, null, 0, 1, null, null, null, null, null, 0,0,0,0,0,0);
        ReceiptEntity receiptEntity2 = new ReceiptEntity(2L, null, null, 0, 1, null, null, null, null, null, 0,0,0,0,0,0);

        List<ReceiptEntity> receiptEntities = new ArrayList<>(Arrays.asList(receiptEntity1, receiptEntity2));

        given(receiptService.getReceipts()).willReturn((ArrayList<ReceiptEntity>) receiptEntities);

        mockMvc.perform(get("/api/receipts/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)));
    }

    @Test
    public void getReceiptById_ShouldReturnReceipt() throws Exception {
        ReceiptEntity receipt = new ReceiptEntity(1L, null, null, 1, 2,null, null, null, null, null, 0,0,0,0,0,0);
        given(receiptService.getReceiptById(1L)).willReturn(receipt);

        mockMvc.perform(get("/api/receipts/{id}",1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.costOfRepair", is(1)))
                .andExpect(jsonPath("$.totalAmount", is(2.0)));
    }

    @Test
    public void getReceiptByCarPlate_ShouldReturnCarPlate() throws Exception {
        ReceiptEntity receipt1 = new ReceiptEntity(1L, null, null, 1, 2,null, null, null, null, "CGZG94", 0,0,0,0,0,0);
        ReceiptEntity receipt2 = new ReceiptEntity(2L, null, null, 3, 4,null, null, null, null, "CGZG94", 0,0,0,0,0,0);
        List<ReceiptEntity> receiptEntities = new ArrayList<>(Arrays.asList(receipt1, receipt2));
        given(receiptService.getReceiptByCarPlate("CGZG94")).willReturn(receiptEntities);

        mockMvc.perform(get("/api/receipts/byPlate/{plate}","CGZG94"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)));
    }

    @Test
    public void saveReceipt_ShouldReturnSavedReceipt() throws Exception {
        ReceiptEntity savedReceipt = new ReceiptEntity(1L, null, null, 1, 2,null, null, null, null, "CGZG94", 0,0,0,0,0,0);
        given(receiptService.saveReceipt(Mockito.any(ReceiptEntity.class), Mockito.any(List.class))).willReturn(savedReceipt);

        String receiptJson = """
                {
                    "carPlate" : "CGZA96"
                }
                """;
        mockMvc.perform(post("/api/receipts/?repairIds=1,2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(receiptJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    public void updateReceipt_ShouldReturnUpdatedReceipt() throws Exception {
        ReceiptEntity updatedReceipt = new ReceiptEntity(1L, null, null, 1, 2,null, null, null, null, "CGZG94", 0,0,0,0,0,0);
        given(receiptService.updateReceipt(Mockito.any(ReceiptEntity.class))).willReturn(updatedReceipt);

        String receiptJson = """
                {
                    "carPlate" : "CGZA96"
                }
                """;

        mockMvc.perform(put("/api/receipts/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(receiptJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    public void mod_out_dates_shouldReturnUpdatedReceipt() throws Exception {
        LocalDate dummyDate = LocalDate.of(2024, 4, 29);
        LocalTime dummyTime = LocalTime.of(11,45);
        ReceiptEntity receipt = new ReceiptEntity(1L, null, null, 1, 2,dummyDate, dummyTime, null, null, null, 0,0,0,0,0,0);
        when(receiptService.modifyOutDatesReceipt(1L, dummyDate, dummyTime)).thenReturn(receipt);

        mockMvc.perform(put("/api/receipts/modify_out_date/1?workshopOutDate=2024-04-29&workshopOutHour=11:45")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.workshopOutDate", is("2024-04-29")))
                .andExpect(jsonPath("$.workshopOutHour", is("11:45:00")));

    }

    @Test
    public void mod_pickUp_dates_shouldReturnUpdatedReceipt() throws Exception {
        LocalDate dummyDate = LocalDate.of(2024, 4, 29);
        LocalTime dummyTime = LocalTime.of(11,45);
        ReceiptEntity receipt = new ReceiptEntity(1L, null, null, 1, 2,null, null, dummyDate, dummyTime, null, 0,0,0,0,0,0);
        when(receiptService.modifyPickUpDatesReceipt(1L, dummyDate, dummyTime)).thenReturn(receipt);

        mockMvc.perform(put("/api/receipts/modify_pickUp_date/1?pickUpDate=2024-04-29&pickUpHour=11:45")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.pickUpDate", is("2024-04-29")))
                .andExpect(jsonPath("$.pickUpHour", is("11:45:00")));

    }

    @Test
    public void deleteReceiptById_ShouldReturn204() throws Exception {
        when(receiptService.deleteReceipt(1L)).thenReturn(true);
        mockMvc.perform(delete("/api/receipts/{id}",1))
                .andExpect(status().isNoContent());
    }
}
