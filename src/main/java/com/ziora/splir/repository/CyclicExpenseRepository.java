package com.ziora.splir.repository;

import com.ziora.splir.model.CyclicExpense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CyclicExpenseRepository extends JpaRepository<CyclicExpense, Long> {
    List<CyclicExpense> findAll();
}