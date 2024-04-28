package com.example.Evaluacion_1_Tingeso.controllers;

import com.example.Evaluacion_1_Tingeso.entities.Report_motor_repairEntity;
import com.example.Evaluacion_1_Tingeso.services.Report_motor_repairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/report_motor_repair")
@CrossOrigin("*")
public class Report_motor_repairController {
    @Autowired
    Report_motor_repairService report_motor_repairService;

    @GetMapping("/")
    public ResponseEntity<List<Report_motor_repairEntity>> listReport_motor_repair() {
        List<Report_motor_repairEntity> reports = report_motor_repairService.getAllReport_motor_repairs();
        return ResponseEntity.ok(reports);
    }

    @GetMapping("/calculate")
    public ResponseEntity<List<Report_motor_repairEntity>> calculateReport_motor_repair() {
        List<Report_motor_repairEntity> reports = report_motor_repairService.getReport_motor_repairs();
        return ResponseEntity.ok(reports);
    }
}
