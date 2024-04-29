package com.example.Evaluacion_1_Tingeso.services;

import com.example.Evaluacion_1_Tingeso.entities.ReceiptRepairsEntity;
import com.example.Evaluacion_1_Tingeso.repositories.ReceiptRepairsRepository;
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

@WebMvcTest(ReceiptRepairsService.class)
public class ReceiptRepairsServiceTest {

    @Autowired
    private ReceiptRepairsService receiptRepairsService;

    @MockBean
    private ReceiptRepairsRepository receiptRepairsRepository;

    @Test
    void whenSaveReceiptRepairs_thenCorrect() {
        ReceiptRepairsEntity receiptRepairsEntity1 = new ReceiptRepairsEntity(1L, 1L, 1L);
        when(receiptRepairsRepository.save(Mockito.any(ReceiptRepairsEntity.class))).thenReturn(receiptRepairsEntity1);

        ReceiptRepairsEntity result = receiptRepairsService.saveReceiptRepairs(receiptRepairsEntity1);
        assertEquals(receiptRepairsEntity1, result);
    }

    @Test
    void whenGetReceiptRepairs_thenCorrect() {
        ReceiptRepairsEntity receiptRepairsEntity1 = new ReceiptRepairsEntity();
        ReceiptRepairsEntity receiptRepairsEntity2 = new ReceiptRepairsEntity();
        List<ReceiptRepairsEntity> receiptRepairsEntities = new ArrayList<>(Arrays.asList(receiptRepairsEntity1, receiptRepairsEntity2));
        when(receiptRepairsRepository.findAll()).thenReturn(receiptRepairsEntities);
        List<ReceiptRepairsEntity> result = receiptRepairsService.getReceiptRepairs();
        assertEquals(2, result.size());
    }


    @Test
    void whenGetReceiptRepairsByReceiptId_thenCorrect() {
        ReceiptRepairsEntity receiptRepairsEntity1 = new ReceiptRepairsEntity(1L, 1L, 1L);
        ReceiptRepairsEntity receiptRepairsEntity2 = new ReceiptRepairsEntity(2L, 1L, 2L);
        List<ReceiptRepairsEntity> receiptRepairsEntities = new ArrayList<>(Arrays.asList(receiptRepairsEntity1, receiptRepairsEntity2));
        when(receiptRepairsRepository.findByReceiptId(1L)).thenReturn(receiptRepairsEntities);

        List<ReceiptRepairsEntity> result = receiptRepairsService.getByReceiptId(1L);
        assertEquals(2, result.size());
    }

    @Test
    void whenUpdateReceiptRepairs_thenCorrect() {
        ReceiptRepairsEntity receiptRepairsEntity1 = new ReceiptRepairsEntity(1L, 1L, 1L);
        when(receiptRepairsRepository.save(receiptRepairsEntity1)).thenReturn(receiptRepairsEntity1);
        ReceiptRepairsEntity updatedReceiptRepairs = receiptRepairsService.updateReceiptRepairs(receiptRepairsEntity1);
        assertEquals(receiptRepairsEntity1, updatedReceiptRepairs);
    }

    @Test
    void whenDeleteReceiptRepairs_thenCorrect() {
        ReceiptRepairsEntity receiptRepairsEntity1 = new ReceiptRepairsEntity(1L, 1L, 1L);

        when(receiptRepairsRepository.save(Mockito.any(ReceiptRepairsEntity.class))).thenReturn(receiptRepairsEntity1);
        boolean result = false;
        try {
            result = receiptRepairsService.deleteReceiptRepairs(1L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Then
        assertThat(result).isTrue();
    }

    @Test
    void whenDeleteReceiptRepairsE_thenCorrect() {

        doThrow(new RuntimeException("java.lang.RuntimeException")).when(receiptRepairsRepository).deleteById(anyLong());

        Exception exception = assertThrows(Exception.class, () -> receiptRepairsService.deleteReceiptRepairs(1L));

        assertEquals("java.lang.RuntimeException", exception.getMessage());
    }
}
