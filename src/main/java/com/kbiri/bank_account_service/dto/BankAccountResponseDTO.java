package com.kbiri.bank_account_service.dto;
import com.kbiri.bank_account_service.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class BankAccountResponseDTO {
    private  String id;
    private Date createDat;
    private Double balance;
    private String currency;
    private AccountType type;
}
