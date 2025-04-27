package com.kbiri.bank_account_service.web;

import com.kbiri.bank_account_service.dto.BankAccountRequestDTO;
import com.kbiri.bank_account_service.dto.BankAccountResponseDTO;
import com.kbiri.bank_account_service.entities.BankAccount;
import com.kbiri.bank_account_service.mappers.AccountMapper;
import com.kbiri.bank_account_service.repositories.BankAccountRepository;
import com.kbiri.bank_account_service.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountRestController {
    private BankAccountRepository bankAccountRepository;
    private AccountService accountService;
    private AccountMapper accountMapper;

    public AccountRestController(BankAccountRepository bankAccountRepository, AccountService accountService, AccountMapper accountMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }
    @GetMapping("/bankAccounts")
    public List<BankAccount>bankAccounts(){
        return bankAccountRepository.findAll();
    }

    @GetMapping("/bankAccounts/{id}")
    public BankAccount bankAccounts(@PathVariable String id){
        return bankAccountRepository.findById(id)
                .orElseThrow(()->new RuntimeException(String.format("Account %s not found",id)));
    }
    @PostMapping("/bankAccounts")
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO RequestDTO){
        return accountService.addAccount(RequestDTO);
    }

    @PutMapping("/bankAccounts/{id}")
    public BankAccount update(@PathVariable String id,@RequestBody BankAccount bankAccount){
        BankAccount account=bankAccountRepository.findById(id).orElseThrow();
        if(bankAccount.getBalance()!=null)account.setBalance(bankAccount.getBalance());
        if(bankAccount.getCurrency()!=null)account.setCurrency(bankAccount.getCurrency());
        if(bankAccount.getType()!=null)account.setType(bankAccount.getType());
        if(bankAccount.getCreateDat()!=null)account.setCreateDat(bankAccount.getCreateDat());
        return bankAccountRepository.save(account);
    }
    @DeleteMapping("/bankAccounts/{id}")
    public  void deleteAccount(@PathVariable String id){
        bankAccountRepository.deleteById(id);

    }
}
