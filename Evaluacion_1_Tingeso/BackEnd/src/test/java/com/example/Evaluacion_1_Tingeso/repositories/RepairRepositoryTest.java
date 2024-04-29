package com.example.Evaluacion_1_Tingeso.repositories;

import com.example.Evaluacion_1_Tingeso.entities.RepairsEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class RepairRepositoryTest {

    @Autowired TestEntityManager entityManager;

    @Autowired
    private RepairsRepository repairsRepository;

    @Test
    public void whenFindByMotorAndRepairName(){
        RepairsEntity repairsEntity = new RepairsEntity(null, "motor", 0, 120000);
        entityManager.persistAndFlush(repairsEntity);

        RepairsEntity found = repairsRepository.findByMotorAndRepairName( repairsEntity.getTypeOfMotor(),repairsEntity.getRepairName());
        assertThat(found.getRepairName()).isEqualTo("motor");
    }
}
