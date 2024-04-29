package com.example.Evaluacion_1_Tingeso.repositories;

import com.example.Evaluacion_1_Tingeso.entities.Report_type_repairEntity;
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
public class Report_type_repairRepositoryTest {

    @Autowired TestEntityManager entityManager;

    @Autowired
    private Report_type_repairRepository reportTypeRepairRepositoryRepository;

    @Test
    public void whenFindByTypeAndRepairName(){
        Report_type_repairEntity reportEntity = new Report_type_repairEntity(null, "Motor", "SUV", 0,0);
        entityManager.persistAndFlush(reportEntity);

        Report_type_repairEntity found = reportTypeRepairRepositoryRepository.findByTypeAndRepairName(reportEntity.getTypeName(),reportEntity.getRepairName());
        assertThat(found.getRepairName()).isEqualTo("Motor");
    }

    @Test void whenFindAllByOrderByAmount(){
        Report_type_repairEntity reportEntity1 = new Report_type_repairEntity(null, "Motor", "SUV", 1,100000);
        Report_type_repairEntity reportEntity2 = new Report_type_repairEntity(  null, "Motor", "Sedán", 0,0);
        List<Report_type_repairEntity> reports = new ArrayList<>(Arrays.asList(reportEntity2,reportEntity1));
        entityManager.persistAndFlush(reportEntity1);
        entityManager.persistAndFlush(reportEntity2);
        List<Report_type_repairEntity> ordered = reportTypeRepairRepositoryRepository.findAllByOrderByAmount();
        assertEquals(ordered.get(0).getId(), 1L);
    }
}
