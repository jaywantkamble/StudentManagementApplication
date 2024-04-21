package com.example.feesmanagement.service;

import com.example.feesmanagement.model.Fees;
import com.example.feesmanagement.repository.FeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FeesService {

    @Autowired
    private FeesRepository feesRepository;

    public Fees addFees(Fees fees) {
        return feesRepository.save(fees);
    }

    public List<Fees> getAllFees() {
        return feesRepository.findAll();
    }

    public Optional<Fees> getFeesById(Long id) {
        return feesRepository.findById(id);
    }

    public void deleteFees(Long id) {
        feesRepository.deleteById(id);
    }

    public List<Fees> getFeesByStudentId(Long studentId) {
        return feesRepository.findByStudentId(studentId);
    }
}
