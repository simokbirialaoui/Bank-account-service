package com.kbiri.bank_account_service.web;

import com.kbiri.bank_account_service.dto.BankAccountRequestDTO;
import com.kbiri.bank_account_service.dto.BankAccountResponseDTO;
import com.kbiri.bank_account_service.entities.BankAccount;
import com.kbiri.bank_account_service.entities.Customer;
import com.kbiri.bank_account_service.repositories.BankAccountRepository;
import com.kbiri.bank_account_service.repositories.CustomerRepository;
import com.kbiri.bank_account_service.service.AccountService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BankAccountGraphQLController {
    private BankAccountRepository bankAccountRepository;
    private AccountService accountService;
    private CustomerRepository customerRepository;

    public BankAccountGraphQLController(BankAccountRepository bankAccountRepository, AccountService accountService, CustomerRepository customerRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.accountService = accountService;
        this.customerRepository = customerRepository;
    }
    @QueryMapping
    public List<BankAccount> accountsList(){
        return bankAccountRepository.findAll();
    }
    @QueryMapping
    public BankAccount accountById(@Argument String id){
        return bankAccountRepository.findById(id).orElseThrow(() -> new RuntimeException(String.format("Account not found with id: " + id)));
    }
    @MutationMapping
    public BankAccountResponseDTO addAccount(@Argument  BankAccountRequestDTO bankAccount){

        return accountService.addAccount(bankAccount);
    }

    @MutationMapping
    public BankAccountResponseDTO updateAccount(@Argument String id,@Argument  BankAccountRequestDTO bankAccount){

        return accountService.updateAccount(id,bankAccount);
    }
    @MutationMapping
    public void deleteAccount(@Argument String id){

        bankAccountRepository.deleteById(id);
    }

    @QueryMapping
    public List<Customer> customers(){
      return   customerRepository.findAll();
    }

    /*
    comme si on a creer class normale avec @DATA @ BUILDER ...
    record BankAccountDTO(Double balance,String type,String currency){
    }*/
}
