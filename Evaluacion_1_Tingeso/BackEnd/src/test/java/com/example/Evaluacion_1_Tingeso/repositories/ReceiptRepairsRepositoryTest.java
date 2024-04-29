package com.example.Evaluacion_1_Tingeso.repositories;

import com.example.Evaluacion_1_Tingeso.entities.ReceiptRepairsEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class ReceiptRepairsRepositoryTest {

    @Autowired TestEntityManager entityManager;

    @Autowired
    private ReceiptRepairsRepository receiptRepairsRepository;

    @Test
    public void whenFindByReceiptId(){
        ReceiptRepairsEntity receiptRepairsEntity1 = new ReceiptRepairsEntity(null, 1L, 1L);
        ReceiptRepairsEntity receiptRepairsEntity2 = new ReceiptRepairsEntity(null, 1L, 2L);
        entityManager.persistAndFlush(receiptRepairsEntity1);
        entityManager.persistAndFlush(receiptRepairsEntity2);

        List<ReceiptRepairsEntity> found = receiptRepairsRepository.findByReceiptId(1L);
        assertEquals(found.size(), 2);
    }
}

