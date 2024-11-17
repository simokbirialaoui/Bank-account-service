package com.lahssini.bank_account_service.entities;

import com.lahssini.bank_account_service.enums.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Entity @AllArgsConstructor @NoArgsConstructor @Builder @Data
public class BankAccount {
    @Id
    private  String id;
    private Date createDat;
    private Double balance;
    private String currency;
    @Enumerated(EnumType.STRING)
    private AccountType type;
@ManyToOne
    private Customer customer;

}

