package com.example.Evaluacion_1_Tingeso.services;

import com.example.Evaluacion_1_Tingeso.entities.ReceiptEntity;
import com.example.Evaluacion_1_Tingeso.repositories.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ReceiptService {

    @Autowired
    ReceiptRepository receiptRepository;

    public ArrayList<ReceiptEntity> getReceipts(){ return (ArrayList<ReceiptEntity>) receiptRepository.findAll(); }

    public ReceiptEntity saveReceipt(ReceiptEntity receipt){return receiptRepository.save(receipt); }

    public ReceiptEntity getReceiptById(Long id) { return receiptRepository.findById(id).get(); }

    public ArrayList<ReceiptEntity> getReceiptByCarPlate(String plate){ return receiptRepository.findByCarPlate(plate); }

    public ReceiptEntity updateRepair(ReceiptEntity carBrand){ return receiptRepository.save(carBrand); }

    public boolean deleteRepair(Long id) throws Exception{
        try{
            receiptRepository.deleteById(id);
            return true;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
