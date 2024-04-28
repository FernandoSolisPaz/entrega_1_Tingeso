package com.example.Evaluacion_1_Tingeso.controllers;

import com.example.Evaluacion_1_Tingeso.entities.Report_time_repairEntity;
import com.example.Evaluacion_1_Tingeso.services.Report_time_repairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/report_time_repair")
@CrossOrigin("*")
public class Report_time_repairController {
    @Autowired
    Report_time_repairService report_time_repairService;

    @GetMapping("/")
    public ResponseEntity<List<Report_time_repairEntity>> listReport_time_repair() {
        List<Report_time_repairEntity> reports = report_time_repairService.getAllReport_time_repairs();
        return ResponseEntity.ok(reports);
    }

    @GetMapping("/calculate")
    public ResponseEntity<List<Report_time_repairEntity>> calculateReport_time_repair() {
        List<Report_time_repairEntity> reports = report_time_repairService.getReport_time_repairs();
        return ResponseEntity.ok(reports);
    }
}
