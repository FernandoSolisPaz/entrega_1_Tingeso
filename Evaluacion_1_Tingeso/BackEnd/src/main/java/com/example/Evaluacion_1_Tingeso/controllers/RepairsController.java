package com.example.Evaluacion_1_Tingeso.controllers;

import com.example.Evaluacion_1_Tingeso.entities.RepairsEntity;
import com.example.Evaluacion_1_Tingeso.services.RepairsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/repairs")
@CrossOrigin("*")
public class RepairsController {
    @Autowired
    RepairsService repairsService;

    @GetMapping("/")
    public ResponseEntity<List<RepairsEntity>> listRepairs() {
        List<RepairsEntity> repairs = repairsService.getRepairs();
        return ResponseEntity.ok(repairs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RepairsEntity> getRepairById(@PathVariable Long id) {
        RepairsEntity repair = repairsService.getRepairById(id);
        return ResponseEntity.ok(repair);
    }

    @PostMapping("/")
    public ResponseEntity<RepairsEntity> saveRepair(@RequestBody RepairsEntity repair) {
        RepairsEntity repairNew = repairsService.saveRepair(repair);
        return ResponseEntity.ok(repairNew);
    }

    @PutMapping("/")
    public ResponseEntity<RepairsEntity> updateRepair(@RequestBody RepairsEntity repair) {
        RepairsEntity repairUpdated = repairsService.updateRepair(repair);
        return ResponseEntity.ok(repairUpdated);
    }

}
