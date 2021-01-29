package com.ziora.splir.payload;

import java.time.LocalDate;

public class SoloExpenseRequest {
    String expenseName;
    Double expenseValue;
    LocalDate localDate;
    Boolean  isCyclic;

    public SoloExpenseRequest(String expenseName, Double expenseValue, LocalDate localDate, Boolean isCyclic) {
        this.expenseName = expenseName;
        this.expenseValue = expenseValue;
        this.localDate = localDate;
        this.isCyclic = isCyclic;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public Double getExpenseValue() {
        return expenseValue;
    }

    public void setExpenseValue(Double expenseValue) {
        this.expenseValue = expenseValue;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public Boolean getCyclic() {
        return isCyclic;
    }

    public void setCyclic(Boolean cyclic) {
        isCyclic = cyclic;
    }
}
