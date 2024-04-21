package com.example.feesmanagement.repository;

import com.example.feesmanagement.model.Fees;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FeesRepository extends JpaRepository<Fees, Long> {

	List<Fees> findByStudentId(Long studentId);
}
