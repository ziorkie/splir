package com.ziora.splir.repository;

import com.ziora.splir.model.SoloExpense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SoloExpenseRepository extends JpaRepository<SoloExpense, Long> {
    List<SoloExpense> findByUserIdAndCreateDateGreaterThanAndCreateDateLessThan(Long userId, LocalDate start, LocalDate end);

}
