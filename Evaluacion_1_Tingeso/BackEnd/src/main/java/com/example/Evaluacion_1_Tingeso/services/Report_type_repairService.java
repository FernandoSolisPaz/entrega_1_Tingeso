package com.example.Evaluacion_1_Tingeso.services;

import com.example.Evaluacion_1_Tingeso.entities.Report_type_repairEntity;
import com.example.Evaluacion_1_Tingeso.entities.ReceiptRepairsEntity;
import com.example.Evaluacion_1_Tingeso.services.ReceiptRepairsService;
import com.example.Evaluacion_1_Tingeso.entities.RepairsEntity;
import com.example.Evaluacion_1_Tingeso.services.RepairsService;
import com.example.Evaluacion_1_Tingeso.entities.ReceiptEntity;
import com.example.Evaluacion_1_Tingeso.services.ReceiptService;
import com.example.Evaluacion_1_Tingeso.entities.CarEntity;
import com.example.Evaluacion_1_Tingeso.services.CarService;
import com.example.Evaluacion_1_Tingeso.repositories.Report_type_repairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Report_type_repairService {

    @Autowired
    Report_type_repairRepository report_type_repairRepository;
    @Autowired
    ReceiptRepairsService receiptRepairsService;
    @Autowired
    RepairsService repairService;
    @Autowired
    ReceiptService receiptService;
    @Autowired
    CarService carService;

    public List<Report_type_repairEntity> getAllReport_type_repairs() {
        return report_type_repairRepository.findAllByOrderByAmount();
    }

    public List<Report_type_repairEntity> getReport_type_repairs() {

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
        String[] typeNames = {
                "Sedán",
                "Hatchback",
                "SUV",
                "Pickup",
                "Furgoneta"
        };
        report_type_repairRepository.deleteAll();
        for (int i = 0; i < repairNames.length; i++) {
            for (int j = 0; j < typeNames.length; j++) {
                Report_type_repairEntity report_type_repair = new Report_type_repairEntity();
                report_type_repair.setRepairName(repairNames[i]);
                report_type_repair.setTypeName(typeNames[j]);
                report_type_repair.setNumberOfRepairs(0);
                report_type_repair.setTotalAmount(0);
                report_type_repairRepository.save(report_type_repair);
            }
        }
        List<ReceiptRepairsEntity> repairsDone = receiptRepairsService.getReceiptRepairs();
        for(ReceiptRepairsEntity repair : repairsDone) {
            ReceiptEntity receipt = receiptService.getReceiptById(repair.getReceiptId());
            CarEntity car = carService.getCarByPlate(receipt.getCarPlate());
            RepairsEntity repairEntity = repairService.getRepairById(repair.getRepairId());

            Report_type_repairEntity report_type_repair = report_type_repairRepository.findByTypeAndRepairName(typeNames[car.getType()], repairEntity.getRepairName());
            report_type_repair.setNumberOfRepairs(report_type_repair.getNumberOfRepairs() + 1);
            report_type_repair.setTotalAmount(report_type_repair.getTotalAmount() + repairEntity.getCostOfRepair());
            report_type_repairRepository.save(report_type_repair);
        }
        List<Report_type_repairEntity> report_type_repairs = report_type_repairRepository.findAllByOrderByAmount();
        return report_type_repairs;
    }

}
