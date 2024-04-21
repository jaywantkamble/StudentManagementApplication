package com.example.receiptmanagement.service;

import com.example.receiptmanagement.exception.ReceiptNotFoundException;
import com.example.receiptmanagement.model.Receipt;
import com.example.receiptmanagement.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepository;

    public List<Receipt> getAllReceipts() {
        return receiptRepository.findAll();
    }

    public Receipt getReceiptById(Long id) {
        return receiptRepository.findById(id)
                .orElseThrow(() -> new ReceiptNotFoundException("Receipt not found with id: " + id));
    }

    public Receipt createReceipt(Receipt receipt) {
        return receiptRepository.save(receipt);
    }

    public Receipt updateReceipt(Long id, Receipt receipt) {
        if (!receiptRepository.existsById(id)) {
            throw new ReceiptNotFoundException("Receipt not found with id: " + id);
        }
        return receiptRepository.save(receipt);
    }

    public void deleteReceipt(Long id) {
        if (!receiptRepository.existsById(id)) {
            throw new ReceiptNotFoundException("Receipt not found with id: " + id);
        }
        receiptRepository.deleteById(id);
    }
}
