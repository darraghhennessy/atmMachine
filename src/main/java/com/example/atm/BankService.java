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
