package com.example.Evaluacion_1_Tingeso.services;

import com.example.Evaluacion_1_Tingeso.entities.ReceiptEntity;
import com.example.Evaluacion_1_Tingeso.repositories.ReceiptRepository;
import com.example.Evaluacion_1_Tingeso.entities.Car_brandEntity;
import com.example.Evaluacion_1_Tingeso.services.Car_brandService;
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
@Service
public class ReceiptService {

    @Autowired
    ReceiptRepository receiptRepository;
    RepairsService repairsService;
    CarService carService;
    Car_brandService car_brandService;

    public List<ReceiptEntity> getReceipts(){ return receiptRepository.findAll(); }

    public ReceiptEntity saveReceipt(ReceiptEntity receipt) {
        ReceiptEntity newReceipt = new ReceiptEntity();
        newReceipt.setWorkshopInDate(LocalDate.now());
        newReceipt.setWorkshopInHour(LocalTime.now());
        newReceipt.setCarPlate(receipt.getCarPlate());
        newReceipt.setTypeOfRepairId(receipt.getTypeOfRepairId());
        RepairsEntity repair_dummy1 = repairsService.getRepairById(receipt.getTypeOfRepairId());
        newReceipt.setTotalAmount(repair_dummy1.getCostOfRepair());
        CarEntity car_dummy1 = carService.getCarByPlate(receipt.getCarPlate());
        Car_brandEntity car_brand_dummy1 = car_brandService.getCarBrandByid(car_dummy1.getCarBrandId());
        if (car_brand_dummy1.getBondAvailable() > 0) {
            newReceipt.setBrandBond(car_brand_dummy1.getAmount());
            car_brand_dummy1.setBondAvailable(car_brand_dummy1.getBondAvailable() - 1);
            car_brandService.updateCarBrand(car_brand_dummy1);
        }else{
            newReceipt.setBrandBond(0);
        }
        DayOfWeek day_in_workshop = receipt.getWorkshopInDate().getDayOfWeek();
        LocalTime hour_in_workshop = receipt.getWorkshopInHour();
        LocalTime start = LocalTime.of(9, 0); // 9:00
        LocalTime end = LocalTime.of(12, 0); //12:00
        if((day_in_workshop.equals(DayOfWeek.MONDAY) || day_in_workshop.equals(DayOfWeek.THURSDAY)) && (hour_in_workshop.isAfter(start) && hour_in_workshop.isBefore(end))){
            newReceipt.setDayOfAttentionDisc(0.1f);
        }else{
            newReceipt.setDayOfAttentionDisc(0);
        }

        return receiptRepository.save(receipt);
    }

    public ReceiptEntity getReceiptById(Long id) { return receiptRepository.findById(id).get(); }

    public List<ReceiptEntity> getReceiptByCarPlate(String plate){ return receiptRepository.findByCarPlate(plate); }

    public ReceiptEntity updateReceipt(ReceiptEntity carBrand){ return receiptRepository.save(carBrand); }

    public boolean deleteReceipt(Long id) throws Exception{
        try{
            receiptRepository.deleteById(id);
            return true;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
