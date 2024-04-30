package com.example.Evaluacion_1_Tingeso.repositories;

import com.example.Evaluacion_1_Tingeso.entities.ReceiptEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class ReceiptRepositoryTest {
    @Autowired TestEntityManager entityManager;

    @Autowired
    private ReceiptRepository receiptRepository;

    @Test
    public void whenFindByCarPlate(){
        ReceiptEntity receipt1 = new ReceiptEntity(null, null, null, 1, 2,null, null, null, null, "CGZG94", 0,0,0,0,0,0);
        ReceiptEntity receipt2 = new ReceiptEntity( null, null, null, 1, 2,null, null, null, null, "CGZG94", 0,0,0,0,0,0);
        List<ReceiptEntity> receipts = new ArrayList<>(Arrays.asList(receipt1, receipt2));
        entityManager.persistAndFlush(receipt1);
        entityManager.persistAndFlush(receipt2);

        List<ReceiptEntity> found = receiptRepository.findByCarPlate(receipt1.getCarPlate());
        assertThat(found.get(0).getCarPlate()).isEqualTo("CGZG94");
    }
}
