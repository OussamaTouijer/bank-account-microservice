package net.touijer.accountservice;

import net.touijer.accountservice.clients.CustomerRestClient;
import net.touijer.accountservice.entitise.BankAccount;
import net.touijer.accountservice.enums.AccountType;
import net.touijer.accountservice.repository.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class AccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(AccountRepository accountRepository, CustomerRestClient customerRestClient) {
        return args -> {//chaque client creer deux account

            customerRestClient.allCustomers().forEach(customer -> {
                accountRepository.save(BankAccount.builder()
                        .accountId(UUID.randomUUID().toString())
                        .balance(Math.random()*80000)
                        .createAt(LocalDate.now())
                        .currency("MAD")
                        .type(AccountType.SAVING_ACCOUNT)
                        .customerId(customer.getId()).build());

                accountRepository.save(BankAccount.builder()
                        .accountId(UUID.randomUUID().toString())
                        .balance(Math.random()*34000)
                        .createAt(LocalDate.now())
                        .currency("MAD")
                        .type(AccountType.SAVING_ACCOUNT)
                        .customerId(customer.getId()).build());

            });


        };
    }
}
