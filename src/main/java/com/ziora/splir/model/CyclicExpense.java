package com.ziora.splir.model;

import javax.persistence.*;

@Entity
@Table(name="cyclic_expenses")
public class CyclicExpense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String expenseName;
    private Double expenseValue;

    public CyclicExpense(Long userId, String expenseName, Double expenseValue) {
        this.userId = userId;
        this.expenseName = expenseName;
        this.expenseValue = expenseValue;
    }

    public CyclicExpense() {
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
}
