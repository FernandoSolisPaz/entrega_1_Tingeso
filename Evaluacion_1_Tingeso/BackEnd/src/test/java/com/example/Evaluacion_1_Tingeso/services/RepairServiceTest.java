package com.example.Evaluacion_1_Tingeso.services;

import com.example.Evaluacion_1_Tingeso.entities.RepairsEntity;
import com.example.Evaluacion_1_Tingeso.repositories.RepairsRepository;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.Optional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@WebMvcTest(RepairsService.class)
public class RepairServiceTest {
    @Autowired
    private RepairsService repairsService;

    @MockBean
    private RepairsRepository repairsRepository;

    @Test
    void whenSaveRepair_thenCorrect() {
        RepairsEntity repairEntity1 = new RepairsEntity(null, "motor", 0,120000);
        when(repairsRepository.save(Mockito.any(RepairsEntity.class))).thenReturn(repairEntity1);

        RepairsEntity result = repairsService.saveRepair(repairEntity1);
        assertEquals(repairEntity1, result);
    }

    @Test
    void whenGetRepairs_thenCorrect() {
        RepairsEntity repairEntity1 = new RepairsEntity();
        RepairsEntity repairEntity2 = new RepairsEntity();
        List<RepairsEntity> repairEntities = new ArrayList<>(Arrays.asList(repairEntity1, repairEntity2));
        when(repairsRepository.findAll()).thenReturn(repairEntities);
        List<RepairsEntity> result = repairsService.getRepairs();
        assertEquals(2, result.size());
    }


    @Test
    void whenGetRepairById_thenCorrect() {
        RepairsEntity repairEntity1 = new RepairsEntity(1L, "motor", 0,120000);
        when(repairsRepository.findById(1L)).thenReturn(Optional.ofNullable(repairEntity1));

        RepairsEntity result = repairsService.getRepairById(1L);
        assertEquals(repairEntity1, result);
    }

    @Test
    void whenUpdateRepair_thenCorrect() {
        RepairsEntity repairEntity1 = new RepairsEntity(1L, "motor", 0,120000);
        when(repairsRepository.save(repairEntity1)).thenReturn(repairEntity1);
        RepairsEntity updatedRepair = repairsService.updateRepair(repairEntity1);
        assertEquals(repairEntity1, updatedRepair);
    }

    @Test
    void whenGetByMotorIdAndRepairName_thenCorrect() {
        RepairsEntity repairEntity1 = new RepairsEntity(1L, "motor", 0,120000);
        when(repairsRepository.findByMotorAndRepairName(0, "motor")).thenReturn(repairEntity1);
        RepairsEntity result = repairsService.getByMotorIdAndRepairName(0, "motor");
        assertEquals(repairEntity1, result);
    }

}
