package com.example.Evaluacion_1_Tingeso.services;

import com.example.Evaluacion_1_Tingeso.entities.*;
import com.example.Evaluacion_1_Tingeso.repositories.Report_type_repairRepository;
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

@WebMvcTest(Report_type_repairService.class)
public class Report_type_repairServiceTest {


    @Autowired
    private Report_type_repairService report_type_repairService;

    @MockBean
    private ReceiptRepairsService receiptRepairsService;

    @MockBean
    private RepairsService repairsService;

    @MockBean
    private ReceiptService receiptService;

    @MockBean
    private CarService carService;
    
    @MockBean
    private Report_type_repairRepository reportTypeRepairRepository;


    @Test
    void whenGetAllReport_type_repairs_thenCorrect() {
        Report_type_repairEntity report_type_repairEntity1 = new Report_type_repairEntity();
        Report_type_repairEntity report_type_repairEntity2 = new Report_type_repairEntity();
        List<Report_type_repairEntity> report_type_repairEntities = new ArrayList<>(Arrays.asList(report_type_repairEntity2,report_type_repairEntity1));
        when(reportTypeRepairRepository.findAllByOrderByAmount()).thenReturn(report_type_repairEntities);
        List<Report_type_repairEntity> ordered = report_type_repairService.getAllReport_type_repairs();
        assertThat(ordered.size()).isEqualTo(2);
    }

    @Test
    void whenGetReport_type_repair_thenCorrect() {
        List<ReceiptRepairsEntity> receiptRepairsEntities = new ArrayList<>(Arrays.asList(new ReceiptRepairsEntity(1L, 1L, 1L)));
        when(receiptRepairsService.getReceiptRepairs()).thenReturn(receiptRepairsEntities);
        when(receiptService.getReceiptById(Mockito.anyLong())).thenReturn(new ReceiptEntity(1L, null, null, 0, 0, null, null,null,null, "CGZG94", 0, 0, 0, 0,0,0));
        when(carService.getCarByPlate(Mockito.anyString())).thenReturn(new CarEntity("CGZG94", 1L, "4x4", 0, 2023, 0, 4, 4000));
        when(repairsService.getRepairById(Mockito.anyLong())).thenReturn(new RepairsEntity(1L, "Braking System", 0, 10000046));
        when(reportTypeRepairRepository.findByTypeAndRepairName(Mockito.anyString(), Mockito.anyString())).thenReturn(new Report_type_repairEntity(1L, "Braking System", "Sedán", 0, 0));
        when(reportTypeRepairRepository.save(new Report_type_repairEntity())).thenReturn(new Report_type_repairEntity());
        Report_type_repairEntity report_type_repairEntity1 = new Report_type_repairEntity(1L, "Braking System", "Sedán", 1,10000046);
        Report_type_repairEntity report_type_repairEntity2 = new Report_type_repairEntity(2L, "Braking System", "Hatchback", 0,0);
        Report_type_repairEntity report_type_repairEntity3 = new Report_type_repairEntity(3L, "Braking System", "SUV", 0,0);
        Report_type_repairEntity report_type_repairEntity4 = new Report_type_repairEntity(4L, "Braking System", "Pickup", 0,0);
        Report_type_repairEntity report_type_repairEntity5 = new Report_type_repairEntity(5L, "Braking System", "Furgoneta", 0,0);
        Report_type_repairEntity report_type_repairEntity6 = new Report_type_repairEntity(6L, "Refrigeration System", "Sedán", 0,0);
        Report_type_repairEntity report_type_repairEntity7 = new Report_type_repairEntity(7L, "Refrigeration System", "Hatchback", 0,0);
        Report_type_repairEntity report_type_repairEntity8 = new Report_type_repairEntity(8L, "Refrigeration System", "SUV", 0,0);
        Report_type_repairEntity report_type_repairEntity9 = new Report_type_repairEntity(9L, "Refrigeration System", "Pickup", 0,0);
        Report_type_repairEntity report_type_repairEntity10 = new Report_type_repairEntity(10L, "Refrigeration System", "Furgoneta", 0,0);
        Report_type_repairEntity report_type_repairEntity11 = new Report_type_repairEntity(11L, "Motor", "Sedán", 0,0);
        Report_type_repairEntity report_type_repairEntity12 = new Report_type_repairEntity(12L, "Motor", "Hatchback", 0,0);
        Report_type_repairEntity report_type_repairEntity13 = new Report_type_repairEntity(13L, "Motor", "SUV", 0,0);
        Report_type_repairEntity report_type_repairEntity14 = new Report_type_repairEntity(14L, "Motor", "Pickup", 0,0);
        Report_type_repairEntity report_type_repairEntity15 = new Report_type_repairEntity(15L, "Motor", "Furgoneta", 0,0);
        Report_type_repairEntity report_type_repairEntity16 = new Report_type_repairEntity(16L, "Transmision", "Sedán", 0,0);
        Report_type_repairEntity report_type_repairEntity17 = new Report_type_repairEntity(17L, "Transmision", "Hatchback", 0,0);
        Report_type_repairEntity report_type_repairEntity18 = new Report_type_repairEntity(18L, "Transmision", "SUV", 0,0);
        Report_type_repairEntity report_type_repairEntity19 = new Report_type_repairEntity(19L, "Transmision", "Pickup", 0,0);
        Report_type_repairEntity report_type_repairEntity20 = new Report_type_repairEntity(20L, "Transmision", "Furgoneta", 0,0);
        Report_type_repairEntity report_type_repairEntity21 = new Report_type_repairEntity(21L, "Electric System", "Sedán", 0,0);
        Report_type_repairEntity report_type_repairEntity22 = new Report_type_repairEntity(22L, "Electric System", "Hatchback", 0,0);
        Report_type_repairEntity report_type_repairEntity23 = new Report_type_repairEntity(23L, "Electric System", "SUV", 0,0);
        Report_type_repairEntity report_type_repairEntity24 = new Report_type_repairEntity(24L, "Electric System", "Pickup", 0,0);
        Report_type_repairEntity report_type_repairEntity25 = new Report_type_repairEntity(25L, "Electric System", "Furgoneta", 0,0);
        Report_type_repairEntity report_type_repairEntity26 = new Report_type_repairEntity(26L, "Escape System", "Sedán", 0,0);
        Report_type_repairEntity report_type_repairEntity27 = new Report_type_repairEntity(27L, "Escape System", "Hatchback", 0,0);
        Report_type_repairEntity report_type_repairEntity28 = new Report_type_repairEntity(28L, "Escape System", "SUV", 0,0);
        Report_type_repairEntity report_type_repairEntity29 = new Report_type_repairEntity(29L, "Escape System", "Pickup", 0,0);
        Report_type_repairEntity report_type_repairEntity30 = new Report_type_repairEntity(30L, "Escape System", "Furgoneta", 0,0);
        Report_type_repairEntity report_type_repairEntity31 = new Report_type_repairEntity(31L, "Tyres and Wheels", "Sedán", 0,0);
        Report_type_repairEntity report_type_repairEntity32 = new Report_type_repairEntity(32L, "Tyres and Wheels", "Hatchback", 0,0);
        Report_type_repairEntity report_type_repairEntity33 = new Report_type_repairEntity(33L, "Tyres and Wheels", "SUV", 0,0);
        Report_type_repairEntity report_type_repairEntity34 = new Report_type_repairEntity(34L, "Tyres and Wheels", "Pickup", 0,0);
        Report_type_repairEntity report_type_repairEntity35 = new Report_type_repairEntity(35L, "Tyres and Wheels", "Furgoneta", 0,0);
        Report_type_repairEntity report_type_repairEntity36 = new Report_type_repairEntity(36L, "Suspension and steering", "Sedán", 0,0);
        Report_type_repairEntity report_type_repairEntity37 = new Report_type_repairEntity(37L, "Suspension and steering", "Hatchback", 0,0);
        Report_type_repairEntity report_type_repairEntity38 = new Report_type_repairEntity(38L, "Suspension and steering", "SUV", 0,0);
        Report_type_repairEntity report_type_repairEntity39 = new Report_type_repairEntity(39L, "Suspension and steering", "Pickup", 0,0);
        Report_type_repairEntity report_type_repairEntity40 = new Report_type_repairEntity(40L, "Suspension and steering", "Furgoneta", 0,0);
        Report_type_repairEntity report_type_repairEntity41 = new Report_type_repairEntity(41L, "AC and Heating System", "Sedán", 0,0);
        Report_type_repairEntity report_type_repairEntity42 = new Report_type_repairEntity(42L, "AC and Heating System", "Hatchback", 0,0);
        Report_type_repairEntity report_type_repairEntity43 = new Report_type_repairEntity(43L, "AC and Heating System", "SUV", 0,0);
        Report_type_repairEntity report_type_repairEntity44 = new Report_type_repairEntity(44L, "AC and Heating System", "Pickup", 0,0);
        Report_type_repairEntity report_type_repairEntity45 = new Report_type_repairEntity(45L, "AC and Heating System", "Furgoneta", 0,0);
        Report_type_repairEntity report_type_repairEntity46 = new Report_type_repairEntity(46L, "Fuel System", "Sedán", 0,0);
        Report_type_repairEntity report_type_repairEntity47 = new Report_type_repairEntity(47L, "Fuel System", "Hatchback", 0,0);
        Report_type_repairEntity report_type_repairEntity48 = new Report_type_repairEntity(48L, "Fuel System", "SUV", 0,0);
        Report_type_repairEntity report_type_repairEntity49 = new Report_type_repairEntity(49L, "Fuel System", "Pickup", 0,0);
        Report_type_repairEntity report_type_repairEntity50 = new Report_type_repairEntity(50L, "Fuel System", "Furgoneta", 0,0);
        Report_type_repairEntity report_type_repairEntity51 = new Report_type_repairEntity(51L, "Windshield and Glass", "Sedán", 0,0);
        Report_type_repairEntity report_type_repairEntity52 = new Report_type_repairEntity(52L, "Windshield and Glass", "Hatchback", 0,0);
        Report_type_repairEntity report_type_repairEntity53 = new Report_type_repairEntity(53L, "Windshield and Glass", "SUV", 0,0);
        Report_type_repairEntity report_type_repairEntity54 = new Report_type_repairEntity(54L, "Windshield and Glass", "Pickup", 0,0);
        Report_type_repairEntity report_type_repairEntity55 = new Report_type_repairEntity(55L, "Windshield and Glass", "Furgoneta", 0,0);
        List<Report_type_repairEntity> report_type_repairEntities = new ArrayList<>(Arrays.asList(report_type_repairEntity1,report_type_repairEntity2,report_type_repairEntity3,report_type_repairEntity4,report_type_repairEntity5,
                report_type_repairEntity6,report_type_repairEntity7,report_type_repairEntity8,report_type_repairEntity9,report_type_repairEntity10,report_type_repairEntity11,report_type_repairEntity12,report_type_repairEntity13,
                report_type_repairEntity14,report_type_repairEntity15,report_type_repairEntity16,report_type_repairEntity17,report_type_repairEntity18,report_type_repairEntity19,report_type_repairEntity20,report_type_repairEntity21,
                report_type_repairEntity22,report_type_repairEntity23,report_type_repairEntity24,report_type_repairEntity25,report_type_repairEntity26,report_type_repairEntity27,report_type_repairEntity28, report_type_repairEntity29,
                report_type_repairEntity30, report_type_repairEntity31,report_type_repairEntity32,report_type_repairEntity33,report_type_repairEntity34,report_type_repairEntity35,report_type_repairEntity36,report_type_repairEntity37,
                report_type_repairEntity38,report_type_repairEntity39,report_type_repairEntity40,report_type_repairEntity41,report_type_repairEntity42,report_type_repairEntity43,report_type_repairEntity44,report_type_repairEntity45,
                report_type_repairEntity46,report_type_repairEntity47,report_type_repairEntity48,report_type_repairEntity49,report_type_repairEntity50,report_type_repairEntity51,report_type_repairEntity52,report_type_repairEntity53,
                report_type_repairEntity54,report_type_repairEntity55));
        when(reportTypeRepairRepository.findAllByOrderByAmount()).thenReturn(report_type_repairEntities);
        List<Report_type_repairEntity> results = report_type_repairService.getReport_type_repairs();
        assertEquals(results.get(0).getId(),report_type_repairEntities.get(0).getId());
    }

}
