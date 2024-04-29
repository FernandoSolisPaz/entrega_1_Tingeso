package com.example.Evaluacion_1_Tingeso.repositories;

import com.example.Evaluacion_1_Tingeso.entities.Report_motor_repairEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("test")
public class Report_motor_repairRepositoryTest {

    @Autowired TestEntityManager entityManager;

    @Autowired
    private Report_motor_repairRepository reportMotorRepairRepositoryRepository;

    @Test
    public void whenFindByMotorAndRepairName(){
        Report_motor_repairEntity reportEntity = new Report_motor_repairEntity(null, 0, "Motor", 0,0);
        entityManager.persistAndFlush(reportEntity);

        Report_motor_repairEntity found = reportMotorRepairRepositoryRepository.findByMotorAndRepairName(reportEntity.getMotorId(),reportEntity.getRepairName());
        assertThat(found.getRepairName()).isEqualTo("Motor");
    }

    @Test void whenFindAllByOrderByAmount(){
        Report_motor_repairEntity reportEntity1 = new Report_motor_repairEntity(null, 0, "Motor", 0,1);
        Report_motor_repairEntity reportEntity2 = new Report_motor_repairEntity(null, 0, "Braking System", 0,0);
        List<Report_motor_repairEntity> reports = new ArrayList<>(Arrays.asList(reportEntity2,reportEntity1));
        entityManager.persistAndFlush(reportEntity1);
        entityManager.persistAndFlush(reportEntity2);
        List<Report_motor_repairEntity> ordered = reportMotorRepairRepositoryRepository.findAllByOrderByAmount();
        assertEquals(ordered.get(0).getId(), 1L);
    }
}
