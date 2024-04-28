package com.example.Evaluacion_1_Tingeso.services;

import com.example.Evaluacion_1_Tingeso.entities.Report_motor_repairEntity;
import com.example.Evaluacion_1_Tingeso.repositories.Report_motor_repairRepository;
import com.example.Evaluacion_1_Tingeso.entities.ReceiptRepairsEntity;
import com.example.Evaluacion_1_Tingeso.services.ReceiptRepairsService;
import com.example.Evaluacion_1_Tingeso.entities.RepairsEntity;
import com.example.Evaluacion_1_Tingeso.services.RepairsService;
import com.example.Evaluacion_1_Tingeso.entities.ReceiptEntity;
import com.example.Evaluacion_1_Tingeso.services.ReceiptService;
import com.example.Evaluacion_1_Tingeso.entities.CarEntity;
import com.example.Evaluacion_1_Tingeso.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Report_motor_repairService {

    @Autowired
    Report_motor_repairRepository report_motor_repairRepository;
    @Autowired
    ReceiptRepairsService receiptRepairsService;
    @Autowired
    RepairsService repairService;
    @Autowired
    ReceiptService receiptService;
    @Autowired
    CarService carService;

    public List<Report_motor_repairEntity> getAllReport_motor_repairs() {
        return report_motor_repairRepository.findAllByOrderByAmount();
    }

    public List<Report_motor_repairEntity> getReport_motor_repairs() {
        String[] repairNames = {
                "Braking System",
                "Refrigeration System",
                "Motor",
                "Transmision",
                "Electric System",
                "Escape System",
                "Tyres and Wheels",
                "Suspension and steering",
                "AC and Heating System",
                "Fuel System",
                "Windshield and Glass"
        };
        report_motor_repairRepository.deleteAll();
        for (int i = 0; i < repairNames.length; i++) {
            for (int j = 0; j < 4; j++){
                Report_motor_repairEntity report_motor_repair = new Report_motor_repairEntity();
                report_motor_repair.setRepairName(repairNames[i]);
                report_motor_repair.setMotorId(j);
                report_motor_repair.setNumberOfRepairs(0);
                report_motor_repair.setTotalAmount(0);
                report_motor_repairRepository.save(report_motor_repair);
            }
        }
        List<ReceiptRepairsEntity> repairsDone = receiptRepairsService.getReceiptRepairs();
        for (ReceiptRepairsEntity repair : repairsDone) {
            ReceiptEntity receipt = receiptService.getReceiptById(repair.getReceiptId());
            CarEntity car = carService.getCarByPlate(receipt.getCarPlate());
            RepairsEntity repairEntity = repairService.getRepairById(repair.getRepairId());

            Report_motor_repairEntity report_motor_repair = report_motor_repairRepository.findByMotorAndRepairName(car.getMotor(), repairEntity.getRepairName());
            report_motor_repair.setNumberOfRepairs(report_motor_repair.getNumberOfRepairs() + 1);
            report_motor_repair.setTotalAmount(report_motor_repair.getTotalAmount() + repairEntity.getCostOfRepair());
            report_motor_repairRepository.save(report_motor_repair);
        }
        List<Report_motor_repairEntity> report_motor_repair = report_motor_repairRepository.findAllByOrderByAmount();
        return report_motor_repair;
    }

}
