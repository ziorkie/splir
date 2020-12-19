package com.ziora.splir.payload;

import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.*;

public class SignUpRequest {
    @NotBlank
    @Size(min = 3, max = 15)
    private String username;

    @NotBlank
    @Size(min = 6, max = 300)
    private String password;

    @NotBlank
    @Size(max = 40)
    @Email
    private String email;

    @NotBlank
    @Size(min = 3, max = 40)
    private String name;

    @NotBlank
    @Size(min=3, max=40)
    private String surname;

    @Min(500000000)
    @Max(999999999)
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private Integer phoneNumber;

    @NotBlank
    @Size(min=26, max=26)
    @NumberFormat(style= NumberFormat.Style.NUMBER)
    private String accountNumber;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSurname() { return surname; }

    public void setSurname(String surname) { this.surname = surname; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}