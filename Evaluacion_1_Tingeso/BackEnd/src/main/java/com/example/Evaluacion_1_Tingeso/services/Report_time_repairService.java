package com.example.Evaluacion_1_Tingeso.services;

import com.example.Evaluacion_1_Tingeso.entities.Report_time_repairEntity;
import com.example.Evaluacion_1_Tingeso.repositories.Report_time_repairRepository;
import com.example.Evaluacion_1_Tingeso.entities.Car_brandEntity;
import com.example.Evaluacion_1_Tingeso.services.Car_brandService;
import com.example.Evaluacion_1_Tingeso.entities.ReceiptEntity;
import com.example.Evaluacion_1_Tingeso.services.ReceiptService;
import com.example.Evaluacion_1_Tingeso.entities.CarEntity;
import com.example.Evaluacion_1_Tingeso.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.Duration;

@Service
public class Report_time_repairService {

    @Autowired
    Report_time_repairRepository report_time_repairRepository;
    @Autowired
    Car_brandService brandService;
    @Autowired
    ReceiptService receiptService;
    @Autowired
    CarService carService;

    public List<Report_time_repairEntity> getAllReport_time_repairs() {
        return report_time_repairRepository.findAllByOrderByHours();
    }

    public List<Report_time_repairEntity> getReport_time_repairs() {

        String[] brandNames = {
                "Toyota",
                "Jeep",
                "Nissan",
                "Chevrolet",
                "Ford",
                "Hyundai"
        };
        report_time_repairRepository.deleteAll();
        for (int j = 0; j < brandNames.length; j++){
            Report_time_repairEntity report_time_repair = new Report_time_repairEntity();
            report_time_repair.setBrandName(brandNames[j]);
            report_time_repair.setNumberOfRepairs(0);
            report_time_repair.setTimeSum(0L);
            report_time_repair.setAvgHours(0);
            report_time_repairRepository.save(report_time_repair);
        }

        List<ReceiptEntity> repairsDone = receiptService.getReceipts();
        for (ReceiptEntity receipt : repairsDone) {
            CarEntity car = carService.getCarByPlate(receipt.getCarPlate());
            Car_brandEntity brand = brandService.getCarBrandById(car.getCarBrandId());
            LocalDate inDate = receipt.getWorkshopInDate();
            LocalTime inHour = receipt.getWorkshopInHour();
            LocalDate outDate = receipt.getWorkshopOutDate();
            LocalTime outHour = receipt.getWorkshopOutHour();
            if(inDate != null && inHour != null && outDate != null && outHour != null) {
                LocalDateTime inTime = LocalDateTime.of(inDate, inHour);
                LocalDateTime outTime = LocalDateTime.of(outDate, outHour);
                Duration duration = Duration.between(inTime, outTime);
                Long durationInSeconds = duration.toSeconds();
                Report_time_repairEntity report_time_repair = report_time_repairRepository.findByBrandName(brand.getBrandName());
                report_time_repair.setNumberOfRepairs(report_time_repair.getNumberOfRepairs() + 1);
                report_time_repair.setTimeSum(report_time_repair.getTimeSum() + durationInSeconds);
                report_time_repairRepository.save(report_time_repair);
            }
        }
        List<Report_time_repairEntity> allReport_time_repairs = report_time_repairRepository.findAll();
        for (Report_time_repairEntity report_time_repair : allReport_time_repairs) {
            Long seconds = report_time_repair.getTimeSum();
            double inHours = seconds / 3600.0;
            report_time_repair.setAvgHours(inHours);
            report_time_repairRepository.save(report_time_repair);
        }
        List<Report_time_repairEntity> report_time_repair = report_time_repairRepository.findAllByOrderByHours();
        return report_time_repair;
    }
}
