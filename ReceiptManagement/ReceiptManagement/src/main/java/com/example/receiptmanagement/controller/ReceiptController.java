package com.example.receiptmanagement.controller;

import com.example.receiptmanagement.exception.ReceiptNotFoundException;
import com.example.receiptmanagement.model.Receipt;
import com.example.receiptmanagement.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {

    @Autowired
    private ReceiptService receiptService;

    @GetMapping
    public ResponseEntity<List<Receipt>> getAllReceipts() {
        List<Receipt> receipts = receiptService.getAllReceipts();
        return ResponseEntity.ok(receipts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receipt> getReceiptById(@PathVariable Long id) {
        Receipt receipt = receiptService.getReceiptById(id);
        return ResponseEntity.ok(receipt);
    }

    @PostMapping
    public ResponseEntity<Receipt> createReceipt(@RequestBody Receipt receipt) {
        Receipt createdReceipt = receiptService.createReceipt(receipt);
        return new ResponseEntity<>(createdReceipt, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Receipt> updateReceipt(@PathVariable Long id, @RequestBody Receipt receipt) {
        Receipt updatedReceipt = receiptService.updateReceipt(id, receipt);
        return ResponseEntity.ok(updatedReceipt);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReceipt(@PathVariable Long id) {
        receiptService.deleteReceipt(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(ReceiptNotFoundException.class)
    public ResponseEntity<String> handleReceiptNotFoundException(ReceiptNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
