package com.example.Evaluacion_1_Tingeso.controllers;

import com.example.Evaluacion_1_Tingeso.entities.ReceiptEntity;
import com.example.Evaluacion_1_Tingeso.services.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/repairs")
@CrossOrigin("*")
public class ReceiptController {
    @Autowired
    ReceiptService receiptService;

    @GetMapping("/")
    public ResponseEntity<List<ReceiptEntity>> listReceipts() {
        List<ReceiptEntity> receipt = receiptService.getReceipts();
        return ResponseEntity.ok(receipt);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReceiptEntity> getReceiptById(@PathVariable Long id) {
        ReceiptEntity receipt = receiptService.getReceiptById(id);
        return ResponseEntity.ok(receipt);
    }

    @GetMapping("/{plate}")
    public ResponseEntity<List<ReceiptEntity>> getReceiptByCarPlate(@PathVariable String plate) {
        List<ReceiptEntity> receipts = receiptService.getReceiptByCarPlate(plate);
        return ResponseEntity.ok(receipts);
    }

    @PostMapping("/")
    public ResponseEntity<ReceiptEntity> saveReceipt(@RequestBody ReceiptEntity receipt) {
        ReceiptEntity receiptNew = receiptService.saveReceipt(receipt);
        return ResponseEntity.ok(receiptNew);
    }

    @PutMapping("/")
    public ResponseEntity<ReceiptEntity> updateReceipt(@RequestBody ReceiptEntity repair) {
        ReceiptEntity repairUpdated = receiptService.updateReceipt(repair);
        return ResponseEntity.ok(repairUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteReceiptById(@PathVariable Long id) throws Exception {
        var isDeleted = receiptService.deleteReceipt(id);
        return ResponseEntity.noContent().build();
    }
}
