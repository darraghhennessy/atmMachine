package com.example.atm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService {

    private final AtmRepository atmRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public BankService(AtmRepository atmRepository, AccountRepository accountRepository) {
        this.atmRepository = atmRepository;
        this.accountRepository = accountRepository;
    }

    public void initializeDatabases() {
        Account account1 = new Account(123456789, 1234, 800, 200);
        Account account2 = new Account(987654321, 4321, 1230, 150);

        Atm atm = new Atm(1, 10, 30, 30, 20);

        atmRepository.save(atm);
        accountRepository.saveAll(List.of(account1, account2));
    }

    public List<Atm> getAtms() {
        return atmRepository.findAll();
    }

    public void setAtm(Atm atm) {
        atmRepository.saveAndFlush(atm);
    }

    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    public void setAccount(Account account) {
        accountRepository.saveAndFlush(account);
    }

}
