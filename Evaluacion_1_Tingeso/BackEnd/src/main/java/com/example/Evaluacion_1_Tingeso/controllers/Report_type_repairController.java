package com.example.Evaluacion_1_Tingeso.controllers;

import com.example.Evaluacion_1_Tingeso.entities.Report_type_repairEntity;
import com.example.Evaluacion_1_Tingeso.services.Report_type_repairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/report_type_repair")
@CrossOrigin("*")
public class Report_type_repairController {
    @Autowired
    Report_type_repairService report_type_repairService;

    @GetMapping("/")
    public ResponseEntity<List<Report_type_repairEntity>> listReport_type_repair() {
        List<Report_type_repairEntity> reports = report_type_repairService.getAllReport_type_repairs();
        return ResponseEntity.ok(reports);
    }

    @GetMapping("/calculate")
    public ResponseEntity<List<Report_type_repairEntity>> calculateReport_type_repair() {
        List<Report_type_repairEntity> reports = report_type_repairService.getReport_type_repairs();
        return ResponseEntity.ok(reports);
    }
}
