package com.ziora.splir.controller;

import com.ziora.splir.model.SoloExpense;
import com.ziora.splir.payload.SoloExpenseRequest;
import com.ziora.splir.security.CurrentUser;
import com.ziora.splir.security.UserPrincipal;
import com.ziora.splir.service.SoloExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/solo")
public class SoloExpenseController {

    @Autowired
    private SoloExpenseService soloExpenseService;

    @PostMapping("/addexpense")
    public ResponseEntity<String> addExpense(@CurrentUser UserPrincipal userPrincipal, @RequestBody SoloExpenseRequest soloExpenseRequest) {
        soloExpenseService.addExpense(userPrincipal.getId(), soloExpenseRequest);
        return new ResponseEntity("Solo expense added!", HttpStatus.OK);
    }

    @GetMapping("/getexpenses")
    public ResponseEntity<List<SoloExpense>> getMonthlyExpenses(@CurrentUser UserPrincipal userPrincipal) {
        List<SoloExpense>  monthlyExpenses =  soloExpenseService.getExpenses(userPrincipal.getId());
        return new ResponseEntity(monthlyExpenses, HttpStatus.OK);
    }







}
