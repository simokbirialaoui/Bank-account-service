package com.kbiri.bank_account_service.service;

import com.kbiri.bank_account_service.dto.BankAccountRequestDTO;
import com.kbiri.bank_account_service.dto.BankAccountResponseDTO;

 public interface AccountService {
    BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountRequestDTO);

     BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO bankAccountDTO);
 }
