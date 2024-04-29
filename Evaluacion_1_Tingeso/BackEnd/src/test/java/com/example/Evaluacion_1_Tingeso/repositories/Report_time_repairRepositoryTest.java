package com.example.Evaluacion_1_Tingeso.repositories;

import com.example.Evaluacion_1_Tingeso.entities.Report_time_repairEntity;
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
public class Report_time_repairRepositoryTest {

    @Autowired TestEntityManager entityManager;

    @Autowired
    private Report_time_repairRepository reportTimeRepairRepositoryRepository;

    @Test
    public void whenFindByBrandName(){
        Report_time_repairEntity reportEntity = new Report_time_repairEntity(null, "Toyota", 0, 0L,0);
        entityManager.persistAndFlush(reportEntity);

        Report_time_repairEntity found = reportTimeRepairRepositoryRepository.findByBrandName(reportEntity.getBrandName());
        assertThat(found.getBrandName()).isEqualTo("Toyota");
    }

    @Test void whenFindAllByOrderByHours(){
        Report_time_repairEntity reportEntity1 = new Report_time_repairEntity(null, "Toyota", 0, 0L,1);
        Report_time_repairEntity reportEntity2 = new Report_time_repairEntity(null, "Nissan", 0, 0L,0);
        List<Report_time_repairEntity> reports = new ArrayList<>(Arrays.asList(reportEntity2,reportEntity1));
        entityManager.persistAndFlush(reportEntity1);
        entityManager.persistAndFlush(reportEntity2);
        List<Report_time_repairEntity> ordered = reportTimeRepairRepositoryRepository.findAllByOrderByHours();
        assertEquals(ordered.get(0).getId(), 2L);
    }
}
