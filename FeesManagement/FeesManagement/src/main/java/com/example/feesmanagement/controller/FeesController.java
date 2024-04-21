package com.example.feesmanagement.controller;

import com.example.feesmanagement.exception.FeeNotFoundException;
import com.example.feesmanagement.model.Fees;
import com.example.feesmanagement.service.FeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fees")
public class FeesController {

    @Autowired
    private FeesService feesService;

    @PostMapping
    public ResponseEntity<Fees> addFees(@RequestBody Fees fees) {
        try {
            Fees addedFees = feesService.addFees(fees);
            return ResponseEntity.status(HttpStatus.CREATED).body(addedFees);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Fees>> getAllFees() {
        try {
            List<Fees> feesList = feesService.getAllFees();
            return ResponseEntity.ok(feesList);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fees> getFeesById(@PathVariable Long id) {
        try {
            Optional<Fees> fees = feesService.getFeesById(id);
            return fees.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Fees>> getFeesByStudentId(@PathVariable Long studentId) {
        try {
            List<Fees> feesList = feesService.getFeesByStudentId(studentId);
            if (feesList.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(feesList);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFees(@PathVariable Long id) {
        try {
            feesService.deleteFees(id);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + ex.getMessage());
    }
    
    @ExceptionHandler(FeeNotFoundException.class)
    public String handleFeeNotFoundException(FeeNotFoundException ex) {
        return ex.getMessage();
    }
}
