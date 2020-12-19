package com.ziora.splir.model;

import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="payment_details")
public class PaymentDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Min(500000000)
    @Max(999999999)
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private Integer phoneNumber;

    @NotBlank
    @Size(min=26, max=26)
    @NumberFormat(style= NumberFormat.Style.NUMBER)
    private String accountNumber;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PaymentDetail() {
    }

    public PaymentDetail(@NotBlank @Size(min = 9, max = 9) Integer phoneNumber, @NotBlank @Size(min = 26, max = 26) String accountNumber) {
        this.phoneNumber = phoneNumber;
        this.accountNumber = accountNumber;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }


}
