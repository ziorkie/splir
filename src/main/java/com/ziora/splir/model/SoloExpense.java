package com.ziora.splir.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="solo_expenses")
public class SoloExpense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long userId;
    String expenseName;
    Double expenseValue=0.0;
    LocalDate createDate;
    Boolean isCyclic;

    public SoloExpense(Long userId, String expenseName, Double expenseValue, LocalDate createDate, Boolean isCyclic) {
        this.userId = userId;
        this.expenseName = expenseName;
        this.expenseValue = expenseValue;
        this.createDate = createDate;
        this.isCyclic = isCyclic;
    }

    public SoloExpense() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public Boolean getCyclic() {
        return isCyclic;
    }

    public void setCyclic(Boolean cyclic) {
        isCyclic = cyclic;
    }
}
