package com.example.Evaluacion_1_Tingeso.services;

import com.example.Evaluacion_1_Tingeso.entities.*;
import com.example.Evaluacion_1_Tingeso.repositories.ReceiptRepository;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@WebMvcTest(ReceiptService.class)
public class ReceiptServiceTest {

    @Autowired
    private ReceiptService receiptService;

    @MockBean
    private RepairsService repairsService;

    @MockBean
    private ReceiptRepairsService receiptRepairsService;

    @MockBean
    private Car_brandService carBrandService;

    @MockBean
    private CarService carService;

    @MockBean
    private ReceiptRepository receiptRepository;

    @Test
    void whenGetCars_thenCorrect() {
        ReceiptEntity receiptEntity1 = new ReceiptEntity();
        ReceiptEntity receiptEntity2 = new ReceiptEntity();
        List<ReceiptEntity> receiptEntities = new ArrayList<>(Arrays.asList(receiptEntity1, receiptEntity2));
        when(receiptRepository.findAll()).thenReturn(receiptEntities);
        List<ReceiptEntity> result = receiptService.getReceipts();
        assertEquals(2, result.size());
    }

    @Test
    void whenGetReceiptByCarPlate(){
        ReceiptEntity receiptEntity1 = new ReceiptEntity(1L, null, null, 0, 0, null, null, null, null, "CGZG94", 0, 0, 0, 0, 0, 0);
        ReceiptEntity receiptEntity2 = new ReceiptEntity(2L, null, null, 0, 0, null, null, null, null, "CGZG94", 0, 0, 0, 0, 0, 0);
        List<ReceiptEntity> receiptEntities = Arrays.asList(receiptEntity1, receiptEntity2);
        when(receiptRepository.findByCarPlate(anyString())).thenReturn(receiptEntities);
        List<ReceiptEntity> result = receiptService.getReceiptByCarPlate("CGZG94");
        assertEquals(receiptEntities, result);
    }

    @Test
    void whenSaveReceipt_thenCorrect() {
        when(carService.getCarByPlate(anyString())).thenReturn(new CarEntity("CGZG94", 1L, "4x4", 0, 2024, 0, 4, 4000));
        when(carBrandService.getCarBrandById(anyLong())).thenReturn(new Car_brandEntity(1L, "Nissan", 3,1000));
        when(carBrandService.updateCarBrand(new Car_brandEntity())).thenReturn(new Car_brandEntity(1L, "Nissan", 2,1000));
        when(receiptRepository.countReceiptEntitiesByNumberOfRepairsIn12Months(anyString())).thenReturn(2);
        when(receiptRepository.save(Mockito.any(ReceiptEntity.class))).thenReturn(new ReceiptEntity(1L, null, null, 0, 0, null, null, null, null, null, 0, 0, 0, 0, 0, 0));
        when(repairsService.getByMotorIdAndRepairName(Mockito.anyInt(), anyString())).thenReturn(new RepairsEntity(2L, "Motor", 0, 0));
        when(receiptRepairsService.saveReceiptRepairs(Mockito.any(ReceiptRepairsEntity.class))).thenReturn(null);
        ReceiptRepairsEntity receiptRepairsEntity = new ReceiptRepairsEntity(null, 1L, 1L);
        List<ReceiptRepairsEntity> receiptsRepairs = new ArrayList<>(Arrays.asList(receiptRepairsEntity));
        when(receiptRepairsService.getByReceiptId(anyLong())).thenReturn(receiptsRepairs);
        when(repairsService.getRepairById(anyLong())).thenReturn(new RepairsEntity(2L, "Motor", 0, 0));
        ReceiptEntity receiptEntity = new ReceiptEntity(1L, null, null, 0, 0, null, null, null, null, null, 0, 0, 0, 0, 0, 0);
        List<Integer> dummy = new ArrayList<>(Arrays.asList(1));
        ReceiptEntity result = receiptService.saveReceipt(new ReceiptEntity(null, null, null, 0, 0, null, null, null, null, "CGZG94", 0, 0, 0, 0, 0, 0), dummy);
        assertEquals(receiptEntity, result);
    }

    @Test
    void whenModifyOutDatesReceipt_thenCorrect() {
        ReceiptEntity receiptEntity = new ReceiptEntity(1L, null, null, 0, 0, null, null, null, null, null, 0, 0, 0, 0, 0, 0);
        LocalDate outDate = LocalDate.now();
        LocalTime outTime = LocalTime.now();
        when(receiptRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(new ReceiptEntity(1L, null, null, 0, 0, null, null, null, null, null, 0, 0, 0, 0, 0, 0)));
        when(receiptRepository.save(Mockito.any(ReceiptEntity.class))).thenReturn(receiptEntity);
        ReceiptEntity result = receiptService.modifyOutDatesReceipt(1L, outDate, outTime);
        receiptEntity.setWorkshopOutDate(outDate);
        receiptEntity.setWorkshopOutHour(outTime);
        assertEquals(receiptEntity, result);
    }

    @Test
    void whenModifyPickUpDatesReceipt_thenCorrect() {
        LocalDate outDate = LocalDate.now();
        LocalTime outTime = LocalTime.now();
        ReceiptEntity receiptEntity = new ReceiptEntity(1L, null, null, 0, 0, outDate, outTime, null, null, null, 0, 0, 0, 0, 0, 0);
        LocalDate pickUpDate = LocalDate.now();
        LocalTime pickUpTime = LocalTime.now();
        when(receiptRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(new ReceiptEntity(1L, null, null, 0, 0, outDate, outTime, null, null, null, 0, 0, 0, 0, 0, 0)));
        when(receiptRepository.save(Mockito.any(ReceiptEntity.class))).thenReturn(receiptEntity);
        ReceiptEntity result = receiptService.modifyPickUpDatesReceipt(1L, pickUpDate, pickUpTime);
        receiptEntity.setPickUpDate(pickUpDate);
        receiptEntity.setPickUpHour(pickUpTime);
        assertEquals(receiptEntity, result);
    }

    @Test
    void whenDeleteReceipt_thenCorrect() {
        ReceiptEntity receiptEntity1 = new ReceiptEntity(1L, null, null, 0, 0, null, null, null, null, null, 0, 0, 0, 0, 0, 0);


        when(receiptRepository.save(Mockito.any(ReceiptEntity.class))).thenReturn(receiptEntity1);
        boolean result = false;
        try {
            result = receiptService.deleteReceipt(1L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Then
        assertThat(result).isTrue();
    }

    @Test
    void whenDeleteReceiptE_thenCorrect() {

        doThrow(new RuntimeException("java.lang.RuntimeException")).when(receiptRepository).deleteById(anyLong());

        Exception exception = assertThrows(Exception.class, () -> receiptService.deleteReceipt(1L));

        assertEquals("java.lang.RuntimeException", exception.getMessage());
    }
}
