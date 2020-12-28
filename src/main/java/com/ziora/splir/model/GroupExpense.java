package com.ziora.splir.model;

import javax.persistence.*;

@Entity
@Table(name="group_expenses")
public class GroupExpense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long userId;
    Long roomId;
    String expenseName;
    Double expenseValue=0.0;



    public GroupExpense(Long userId, Long eventId, String expenseName, Double expenseValue) {

        this.userId = userId;
        this.roomId = roomId;
        this.expenseName = expenseName;
        this.expenseValue = expenseValue;
    }

    public GroupExpense() {
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

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
}
