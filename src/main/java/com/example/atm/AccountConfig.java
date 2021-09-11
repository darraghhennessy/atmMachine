package com.example.atm;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AccountConfig {

    @Bean
    CommandLineRunner commandLineRunner(AccountRepository accountRepository) {
        return args -> { Account account1 = new Account(123456789, 1234, 800, 200);
            Account account2 = new Account(987654321, 4321, 1230, 150);

            accountRepository.saveAll(List.of(account1, account2));

        };
    }
}
