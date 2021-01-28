package com.ziora.splir.service;

import com.ziora.splir.model.CyclicExpense;
import com.ziora.splir.model.SoloExpense;
import com.ziora.splir.payload.SoloExpenseRequest;
import com.ziora.splir.repository.CyclicExpenseRepository;
import com.ziora.splir.repository.SoloExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SoloExpenseService {

    @Autowired
    private SoloExpenseRepository soloExpenseRepository;

    @Autowired
    private CyclicExpenseRepository cyclicExpenseRepository;

    public void addExpense(Long userId, SoloExpenseRequest soloExpenseRequest){

        if(soloExpenseRequest.getCyclic()==true){
            CyclicExpense cyclicExpense = new CyclicExpense(userId, soloExpenseRequest.getExpenseName(), soloExpenseRequest.getExpenseValue());
            cyclicExpenseRepository.save(cyclicExpense);
        }

            SoloExpense soloExpense = new SoloExpense(userId, soloExpenseRequest.getExpenseName(), soloExpenseRequest.getExpenseValue(),
                    soloExpenseRequest.getLocalDate(), soloExpenseRequest.getCyclic());
            soloExpenseRepository.save(soloExpense);


    }

    public List<SoloExpense> getExpenses(Long userId){
        LocalDate start = LocalDate.ofEpochDay(System.currentTimeMillis() / (24 * 60 * 60 * 1000) ).withDayOfMonth(1);
        LocalDate end = LocalDate.ofEpochDay(System.currentTimeMillis() / (24 * 60 * 60 * 1000) ).plusMonths(1).withDayOfMonth(1).minusDays(1);
        List<SoloExpense> soloExpenseList = soloExpenseRepository.findByUserIdAndCreateDateGreaterThanAndCreateDateLessThan(userId, start, end);
        return soloExpenseList;
    }



    @Scheduled(cron="0 0 0 1 1/1 *")
    public void addCyclic(){
        List<CyclicExpense> cyclicExpenseList = cyclicExpenseRepository.findAll();

        for (CyclicExpense ce:cyclicExpenseList) {
            SoloExpense soloExpense = new SoloExpense(ce.getUserId(), ce.getExpenseName(), ce.getExpenseValue(), LocalDate.now(), true );
            soloExpenseRepository.save(soloExpense);
        }

    }




}
