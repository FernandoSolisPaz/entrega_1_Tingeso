package com.example.Evaluacion_1_Tingeso.services;

import com.example.Evaluacion_1_Tingeso.entities.*;
import com.example.Evaluacion_1_Tingeso.repositories.Report_motor_repairRepository;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
@WebMvcTest(Report_motor_repairService.class)
public class Report_motor_repairServiceTest {

    @Autowired
    private Report_motor_repairService report_motor_repairService;

    @MockBean
    private ReceiptRepairsService receiptRepairsService;

    @MockBean
    private RepairsService repairsService;

    @MockBean
    private ReceiptService receiptService;

    @MockBean
    private CarService carService;

    @MockBean
    private Report_motor_repairRepository reportMotorRepairRepository;

    @Test
    void whenGetAllReport_type_repairs_thenCorrect() {
        Report_motor_repairEntity report_motor_repairEntity1 = new Report_motor_repairEntity();
        Report_motor_repairEntity report_motor_repairEntity2 = new Report_motor_repairEntity();
        List<Report_motor_repairEntity> report_motor_repairEntities = new ArrayList<>(Arrays.asList(report_motor_repairEntity2,report_motor_repairEntity1));
        when(reportMotorRepairRepository.findAllByOrderByAmount()).thenReturn(report_motor_repairEntities);
        List<Report_motor_repairEntity> ordered = report_motor_repairService.getAllReport_motor_repairs();
        assertThat(ordered.size()).isEqualTo(2);
    }

    @Test
    void whenGetReport_motor_repair_thenCorrect() {
        List<ReceiptRepairsEntity> receiptRepairsEntities = new ArrayList<>(Arrays.asList(new ReceiptRepairsEntity(1L, 1L, 1L)));
        when(receiptRepairsService.getReceiptRepairs()).thenReturn(receiptRepairsEntities);
        when(receiptService.getReceiptById(Mockito.anyLong())).thenReturn(new ReceiptEntity(1L, null, null, 0, 0, null, null,null,null, "CGZG94", 0, 0, 0, 0,0,0));
        when(carService.getCarByPlate(Mockito.anyString())).thenReturn(new CarEntity("CGZG94", 1L, "4x4", 0, 2023, 0, 4, 4000));
        when(repairsService.getRepairById(Mockito.anyLong())).thenReturn(new RepairsEntity(1L, "Braking System", 0, 10000046));
        when(reportMotorRepairRepository.findByMotorAndRepairName(Mockito.anyInt(), Mockito.anyString())).thenReturn(new Report_motor_repairEntity(1L, 0, "Braking System", 0, 0));
        when(reportMotorRepairRepository.save(new Report_motor_repairEntity())).thenReturn(new Report_motor_repairEntity());
        Report_motor_repairEntity report_motor_repairEntity1 = new Report_motor_repairEntity(1L, 0, "Braking System", 1,10000046);
        List<Report_motor_repairEntity> report_motor_repairEntities = new ArrayList<>(Arrays.asList(report_motor_repairEntity1));

        String[] repairNames = {
                "Braking System",
                "Refrigeration System",
                "Motor",
                "Transmision",
                "Electric System",
                "Escape System",
                "Tyres and Wheels",
                "Suspension and steering",
                "AC and Heating System",
                "Fuel System",
                "Windshield and Glass"
        };
        Long count = 0L;
        for(int i = 0; i < repairNames.length; i++){
            for(int j = 0; j < 4; j++){
                if(j != 0 && repairNames[i] != "Braking System") {
                    count = count + 1L;
                    Report_motor_repairEntity dummy1 = new Report_motor_repairEntity(count, j, repairNames[i], 0, 0);
                    report_motor_repairEntities.add(dummy1);
                }
            }
        }
        when(reportMotorRepairRepository.findAllByOrderByAmount()).thenReturn(report_motor_repairEntities);
        List<Report_motor_repairEntity> results = report_motor_repairService.getReport_motor_repairs();
        assertEquals(results.get(0).getId(), report_motor_repairEntity1.getId());
    }
}
