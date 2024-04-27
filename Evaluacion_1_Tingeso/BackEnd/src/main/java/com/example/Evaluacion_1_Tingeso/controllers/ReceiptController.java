package com.example.Evaluacion_1_Tingeso.controllers;

import com.example.Evaluacion_1_Tingeso.entities.ReceiptEntity;
import com.example.Evaluacion_1_Tingeso.services.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/receipts")
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

    @GetMapping("/byPlate/{plate}")
    public ResponseEntity<List<ReceiptEntity>> getReceiptByCarPlate(@PathVariable String plate) {
        List<ReceiptEntity> receipts = receiptService.getReceiptByCarPlate(plate);
        return ResponseEntity.ok(receipts);
    }

    @PostMapping("/")
    public ResponseEntity<ReceiptEntity> saveReceipt(@RequestBody ReceiptEntity receipt, @RequestParam("repairIds") List<Integer> repairIds) {
        ReceiptEntity receiptNew = receiptService.saveReceipt(receipt, repairIds);
        return ResponseEntity.ok(receiptNew);
    }

    @PutMapping("/")
    public ResponseEntity<ReceiptEntity> updateReceipt(@RequestBody ReceiptEntity repair) {
        ReceiptEntity repairUpdated = receiptService.updateReceipt(repair);
        return ResponseEntity.ok(repairUpdated);
    }

    @PutMapping("/modify_out_date/{id}")
    public ResponseEntity<ReceiptEntity> mod_out_dates(@PathVariable Long id, @RequestParam("workshopOutDate") LocalDate workshopOutDate, @RequestParam("workshopOutHour") LocalTime workshopOutHour) {
        LocalTime dummy = workshopOutHour;
        ReceiptEntity moddedReceipt = receiptService.modifyOutDatesReceipt(id, workshopOutDate, dummy);
        return ResponseEntity.ok(moddedReceipt);
    }

    @PutMapping("/modify_pickUp_date/{id}")
    public ResponseEntity<ReceiptEntity> mod_pickUp_dates(@PathVariable Long id, @RequestParam("pickUpDate") LocalDate pickUpDate, @RequestParam("pickUpHour") LocalTime pickUpHour) {
        ReceiptEntity moddedReceipt = receiptService.modifyPickUpDatesReceipt(id, pickUpDate, pickUpHour);
        return ResponseEntity.ok(moddedReceipt);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteReceiptById(@PathVariable Long id) throws Exception {
        var isDeleted = receiptService.deleteReceipt(id);
        return ResponseEntity.noContent().build();
    }
}
