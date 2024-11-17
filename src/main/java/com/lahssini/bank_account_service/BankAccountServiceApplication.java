package com.lahssini.bank_account_service;

import com.lahssini.bank_account_service.entities.BankAccount;
import com.lahssini.bank_account_service.entities.Customer;
import com.lahssini.bank_account_service.enums.AccountType;
import com.lahssini.bank_account_service.repositories.BankAccountRepository;
import com.lahssini.bank_account_service.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class BankAccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAccountServiceApplication.class, args);
		System.out.println("hanane");
	}
	@Bean
	CommandLineRunner start(BankAccountRepository bankAccountRepository, CustomerRepository customerRepository){
		return args -> {
			Stream.of("Mohammed","Hanane","Janat").forEach(c-> {
						Customer customer = Customer.builder()
								.name(c)
								.build();
						customerRepository.save(customer);
					}
					);
			customerRepository.findAll().forEach(customer -> {
						for(int i=0;i<10;i++){
							BankAccount bankAccount=BankAccount.builder()
									.id(UUID.randomUUID().toString())
									.type(Math.random()>0.5? AccountType.CURRENY_ACCOUNT:AccountType.SAVING_ACCOUNT)
									.balance(1000+Math.random()*9000)
									.createDat(new Date())
									.customer(customer)
									.currency("MAD")
									.build();
							bankAccountRepository.save(bankAccount);
						}
					}

					);
		};
	}

}
