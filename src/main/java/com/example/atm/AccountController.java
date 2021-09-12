package com.example.atm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/account/balance")
    public int getBalance(@RequestParam("accountNo") int accountNo, @RequestParam("pin") int pin) {
        List<Account> accountList = accountService.getAtm();
        for (Account account: accountList) {
            if (account.getAccountNo() == accountNo) {
                if (account.getPin() != pin) {
                    System.out.println("Incorrect PIN");
                    return 0;
                }
                return account.getOpeningBalance();
            }
        }
        return 0;
    }


}
