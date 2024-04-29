package com.example.Evaluacion_1_Tingeso.services;


import com.example.Evaluacion_1_Tingeso.entities.*;
import com.example.Evaluacion_1_Tingeso.repositories.Report_time_repairRepository;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
@WebMvcTest(Report_time_repairService.class)
public class Report_time_repairServiceTest {

    @Autowired
    private Report_time_repairService report_time_repairService;

    @MockBean
    private ReceiptService receiptService;

    @MockBean
    private CarService carService;

    @MockBean
    private Report_time_repairRepository reportTimeRepairRepository;

    @MockBean
    private Car_brandService carBrandService;

    @Test
    void whenGetAllReport_time_repairs_thenCorrect() {
        Report_time_repairEntity report_time_repairEntity1 = new Report_time_repairEntity();
        Report_time_repairEntity report_time_repairEntity2 = new Report_time_repairEntity();
        List<Report_time_repairEntity> report_time_repairEntities = new ArrayList<>(Arrays.asList(report_time_repairEntity2,report_time_repairEntity1));
        when(reportTimeRepairRepository.findAllByOrderByHours()).thenReturn(report_time_repairEntities);
        List<Report_time_repairEntity> ordered = report_time_repairService.getAllReport_time_repairs();
        assertThat(ordered.size()).isEqualTo(2);
    }

    @Test
    void whenGetReport_time_repair_thenCorrect() {
        LocalDate initialDate = LocalDate.of(2023,4,29);
        LocalTime initialTime = LocalTime.of(11,45);
        LocalDate finalDate = LocalDate.of(2023,4,29);
        LocalTime finalTime = LocalTime.of(11,50);
        List<ReceiptEntity> receiptRepairsEntities = new ArrayList<>(Arrays.asList(new ReceiptEntity(1L, initialDate, initialTime, 0, 0, finalDate, finalTime, null, null, "CGZG94", 0, 0, 0, 0, 0, 0)));
        when(receiptService.getReceipts()).thenReturn(receiptRepairsEntities);
        when(carService.getCarByPlate(Mockito.anyString())).thenReturn(new CarEntity("CGZG94", 1L, "4x4", 0, 2023, 0, 4, 4000));
        when(carBrandService.getCarBrandById(Mockito.anyLong())).thenReturn(new Car_brandEntity(1L, "Toyota", 1, 10000));
        when(reportTimeRepairRepository.findByBrandName(Mockito.anyString())).thenReturn(new Report_time_repairEntity(1L, "Nissan", 0, 0L, 0));
        when(reportTimeRepairRepository.save(new Report_time_repairEntity())).thenReturn(new Report_time_repairEntity());
        Report_time_repairEntity report_time_repairEntity1 = new Report_time_repairEntity(1L, "Toyota", 1, 300L,0);
        List<Report_time_repairEntity> report_time_repairEntities = new ArrayList<>(Arrays.asList(report_time_repairEntity1));

        String[] brandNames = {
                "Toyota",
                "Jeep",
                "Nissan",
                "Chevrolet",
                "Ford",
                "Hyundai"
        };

        Long count = 0L;
        for(int i = 0; i < brandNames.length; i++){
            for(int j = 0; j < 4; j++){
                if(j != 0 && brandNames[i] != "Toyota") {
                    count = count + 1L;
                    Report_time_repairEntity dummy1 = new Report_time_repairEntity(count, brandNames[i], 0, 0L, 0);
                    report_time_repairEntities.add(dummy1);
                }
            }
        }
        when(reportTimeRepairRepository.findAll()).thenReturn(report_time_repairEntities);
        when(reportTimeRepairRepository.findAllByOrderByHours()).thenReturn(report_time_repairEntities);
        List<Report_time_repairEntity> results = report_time_repairService.getReport_time_repairs();
        assertEquals(results.get(0).getId(), report_time_repairEntity1.getId());
    }
}
