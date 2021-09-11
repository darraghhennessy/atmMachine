package com.example.atm;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AtmConfig {

    @Bean
    CommandLineRunner commandLineRunner2(AtmRepository atmRepository) {
        return args -> { Atm atm = new Atm(10, 30, 30, 20);

            atmRepository.saveAll(List.of(atm));

        };
    }
}
