package com.example.Evaluacion_1_Tingeso.repositories;


import com.example.Evaluacion_1_Tingeso.entities.ReceiptRepairsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceiptRepairsRepository extends JpaRepository<ReceiptRepairsEntity, Long>{

    List<ReceiptRepairsEntity> findByReceiptId(Long receiptId);

    List<ReceiptRepairsEntity> findByRepairId(Long repairId);

}
