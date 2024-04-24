package com.example.Evaluacion_1_Tingeso.services;

import com.example.Evaluacion_1_Tingeso.entities.ReceiptEntity;
import com.example.Evaluacion_1_Tingeso.repositories.ReceiptRepository;
import com.example.Evaluacion_1_Tingeso.entities.Car_brandEntity;
import com.example.Evaluacion_1_Tingeso.services.Car_brandService;
import com.example.Evaluacion_1_Tingeso.entities.ReceiptRepairsEntity;
import com.example.Evaluacion_1_Tingeso.services.ReceiptRepairsService;
import com.example.Evaluacion_1_Tingeso.entities.RepairsEntity;
import com.example.Evaluacion_1_Tingeso.services.RepairsService;
import com.example.Evaluacion_1_Tingeso.entities.CarEntity;
import com.example.Evaluacion_1_Tingeso.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.DayOfWeek;
import java.time.Period;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
@Service
public class ReceiptService {

    @Autowired
    ReceiptRepository receiptRepository;
    @Autowired
    RepairsService repairsService;
    @Autowired
    ReceiptRepairsService receiptRepairsService;
    @Autowired
    CarService carService;
    @Autowired
    Car_brandService car_brandService;

    public List<ReceiptEntity> getReceipts(){ return receiptRepository.findAll(); }

    public ReceiptEntity saveReceipt(ReceiptEntity receipt, List<Integer> repairIds) {
        float[][] matrixRepairs = {
                {0.05f, 0.07f, 0.1f, 0.08f},
                {0.1f, 0.12f, 0.15f, 0.13f},
                {0.15f, 0.17f, 0.2f, 0.18f},
                {0.2f, 0.22f, 0.25f, 0.23f}
        };
        float[][] matrixVehicleOldness = {
                {0.05f, 0.05f, 0.07f, 0.07f, 0.07f},
                {0.09f, 0.09f, 0.11f, 0.11f, 0.11f},
                {0.15f, 0.15f, 0.2f, 0.2f, 0.2f}
        };
        float[][] matrixKilometers = {
                {0.03f, 0.03f, 0.05f, 0.05f, 0.05f},
                {0.07f, 0.07f, 0.09f, 0.09f, 0.09f},
                {0.12f, 0.12f, 0.12f, 0.12f, 0.12f},
                {0.2f, 0.2f, 0.2f, 0.2f, 0.2f}
        };
        ReceiptEntity newReceipt = new ReceiptEntity();

        newReceipt.setWorkshopInDate(LocalDate.now());
        newReceipt.setWorkshopInHour(LocalTime.now());

        newReceipt.setCarPlate(receipt.getCarPlate());
        String carPlate = receipt.getCarPlate();
        CarEntity car_dummy1 = carService.getCarByPlate(carPlate);
        Car_brandEntity car_brand_dummy1 = car_brandService.getCarBrandByid(car_dummy1.getCarBrandId());
        if (car_brand_dummy1.getBondAvailable() > 0) {
            newReceipt.setBrandBond(car_brand_dummy1.getAmount());
            car_brand_dummy1.setBondAvailable(car_brand_dummy1.getBondAvailable() - 1);
            car_brandService.updateCarBrand(car_brand_dummy1);
        }else{
            newReceipt.setBrandBond(0);
        }
        DayOfWeek day_in_workshop = newReceipt.getWorkshopInDate().getDayOfWeek();
        LocalTime hour_in_workshop = newReceipt.getWorkshopInHour();
        LocalTime start = LocalTime.of(9, 0); // 9:00
        LocalTime end = LocalTime.of(12, 0); //12:00
        if((day_in_workshop.equals(DayOfWeek.MONDAY) || day_in_workshop.equals(DayOfWeek.THURSDAY)) && (hour_in_workshop.isAfter(start) && hour_in_workshop.isBefore(end))){
            newReceipt.setDayOfAttentionDisc(0.1f);
        }else{
            newReceipt.setDayOfAttentionDisc(0);
        }
        int number_repairs_in_12_months = receiptRepository.countReceiptEntitiesByNumberOfRepairsIn12Months(receipt.getCarPlate());
        int typeOfMotor = car_dummy1.getMotor();
        if(number_repairs_in_12_months>=1 && number_repairs_in_12_months <= 2){
            newReceipt.setNumberOfRepairsDisc(matrixRepairs[0][typeOfMotor]);
        } else if (number_repairs_in_12_months>=3 && number_repairs_in_12_months <=5) {
            newReceipt.setNumberOfRepairsDisc(matrixRepairs[1][typeOfMotor]);
        } else if (number_repairs_in_12_months>=6 && number_repairs_in_12_months <= 9) {
            newReceipt.setNumberOfRepairsDisc(matrixRepairs[2][typeOfMotor]);
        } else if (number_repairs_in_12_months >= 10){
            newReceipt.setNumberOfRepairsDisc(matrixRepairs[3][typeOfMotor]);
        } else {
            newReceipt.setNumberOfRepairsDisc(0);
        }
        LocalDate hoy = LocalDate.now();
        Period years = Period.between(car_dummy1.getYearOfFabrication(), hoy);
        int yearsOld = years.getYears();
        int typeOfVehicle = car_dummy1.getType();
        if(yearsOld>=6 && yearsOld <= 10){
            newReceipt.setAgeVehicleSurcharge(matrixVehicleOldness[0][typeOfVehicle]);
        } else if (yearsOld>=11 && yearsOld <= 15) {
            newReceipt.setAgeVehicleSurcharge(matrixVehicleOldness[1][typeOfVehicle]);
        } else if (yearsOld >= 16) {
            newReceipt.setAgeVehicleSurcharge(matrixVehicleOldness[2][typeOfVehicle]);
        } else {
            newReceipt.setAgeVehicleSurcharge(0);
        }
        int kilometersOfVehicle = car_dummy1.getKilometers();
        if(kilometersOfVehicle >= 5001 && kilometersOfVehicle <= 12000){
            newReceipt.setKilometersSurcharge(matrixKilometers[0][typeOfVehicle]);
        } else if (kilometersOfVehicle >= 12001 && kilometersOfVehicle <= 25000) {
            newReceipt.setKilometersSurcharge(matrixKilometers[1][typeOfVehicle]);
        } else if (kilometersOfVehicle >= 25001 && kilometersOfVehicle <= 40000) {
            newReceipt.setKilometersSurcharge(matrixKilometers[2][typeOfVehicle]);
        } else if (kilometersOfVehicle >= 40001) {
            newReceipt.setKilometersSurcharge(matrixKilometers[3][typeOfVehicle]);
        } else {
            newReceipt.setKilometersSurcharge(0);
        }

        ReceiptEntity dummy = receiptRepository.save(newReceipt);
        for(Integer Id: repairIds){
            ReceiptRepairsEntity dummy2 = new ReceiptRepairsEntity();
            dummy2.setReceiptId(dummy.getReceiptId());
            dummy2.setRepairId(Integer.toUnsignedLong(Id));
            receiptRepairsService.saveReceiptRepairs(dummy2);
        }

        List<ReceiptRepairsEntity> repairs = receiptRepairsService.getByReceiptId(dummy.getReceiptId());
        int repairsSum = 0;
        for (ReceiptRepairsEntity receiptRepairsDummy : repairs) {

            RepairsEntity repair = repairsService.getRepairById(receiptRepairsDummy.getRepairId());
            repairsSum = repairsSum + repair.getCostOfRepair();
        }
        dummy.setCostOfRepair(repairsSum);

        return updateReceipt(dummy);
    }

    public ReceiptEntity getReceiptById(Long id) { return receiptRepository.findById(id).get(); }

    public List<ReceiptEntity> getReceiptByCarPlate(String plate){ return receiptRepository.findByCarPlate(plate); }

    public ReceiptEntity updateReceipt(ReceiptEntity receipt){
        if(receipt.getWorkshopOutDate()!= null && receipt.getWorkshopOutHour() != null && receipt.getPickUpDate() != null && receipt.getPickUpHour() != null){
            LocalDateTime workshopOut = receipt.getWorkshopOutDate().atTime(receipt.getWorkshopOutHour());
            LocalDateTime pickUp = receipt.getPickUpDate().atTime(receipt.getPickUpHour());
            long days_passed_between_out_pickup = ChronoUnit.DAYS.between(workshopOut, pickUp);
            float late_pickup_surcharge = 0.05f * days_passed_between_out_pickup;
            receipt.setDelayOfPickUpSurcharge(late_pickup_surcharge);
        } else if (receipt.getWorkshopOutDate() != null && receipt.getWorkshopOutHour() != null) {
            LocalDateTime workshopOut = receipt.getWorkshopOutDate().atTime(receipt.getWorkshopOutHour());
            LocalDateTime today = LocalDateTime.now();
            long days_passed_between_out_today = ChronoUnit.DAYS.between(workshopOut, today);
            float late_pickup_surcharge = 0.05f * days_passed_between_out_today;
            receipt.setDelayOfPickUpSurcharge(late_pickup_surcharge);
        } else {
            float late_pickup_surcharge = 0;
            receipt.setDelayOfPickUpSurcharge(late_pickup_surcharge);
        }
        int repairsSum = receipt.getCostOfRepair();
        float cost = (repairsSum - receipt.getBrandBond() - repairsSum * receipt.getDayOfAttentionDisc() - repairsSum * receipt.getNumberOfRepairsDisc() + repairsSum * receipt.getAgeVehicleSurcharge() + repairsSum * receipt.getKilometersSurcharge() + repairsSum * receipt.getDelayOfPickUpSurcharge());
        float totalCost = cost + cost * Float.parseFloat(System.getenv("IVA"));
        receipt.setTotalAmount(totalCost);
        return receiptRepository.save(receipt);
    }

    public boolean deleteReceipt(Long id) throws Exception{
        try{
            receiptRepository.deleteById(id);
            return true;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
