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

    @GetMapping("/{id}")
    public ResponseEntity<Report_type_repairEntity> getReport_type_repairById(@PathVariable Long id) {
        Report_type_repairEntity report_type = report_type_repairService.getReport_type_repairById(id);
        return ResponseEntity.ok(report_type);
    }

    @PostMapping("/")
    public ResponseEntity<Report_type_repairEntity> saveReport_type_repair(@RequestBody Report_type_repairEntity report_type_repair) {
        Report_type_repairEntity reportNew = report_type_repairService.saveReportTypeRepair(report_type_repair);
        return ResponseEntity.ok(reportNew);
    }

    @PutMapping("/")
    public ResponseEntity<Report_type_repairEntity> updateReport_type_repair(@RequestBody Report_type_repairEntity report_type_repair) {
        Report_type_repairEntity reportUpdated = report_type_repairService.updateReportTypeRepair(report_type_repair);
        return ResponseEntity.ok(reportUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Report_type_repairEntity> deleteReport_type_repairById(@PathVariable Long id) throws Exception {
        var isDeleted = report_type_repairService.deleteReportTypeRepair(id);
        return ResponseEntity.noContent().build();
    }
}
