package com.example.atm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BankController {

    private final BankService bankService;

    @Autowired
    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping("/balance")
    public String getBalance(@RequestParam("accountNo") int accountNo, @RequestParam("pin") int pin) {
        List<Account> accountList = bankService.getAccounts();
        for (Account account: accountList) {
            if (account.getAccountNo() == accountNo) {
                if (account.getPin() != pin) {
                    return ("Incorrect PIN");
                }
                return ("Your account's opening balance is: " + account.getOpeningBalance());
            }
        }
        return ("Account not found");
    }

    @GetMapping("/max")
    public String getMaximumWithdrawal(@RequestParam("accountNo") int accountNo, @RequestParam("pin") int pin) {
        List<Account> accountList = bankService.getAccounts();
        for (Account account: accountList) {
            if (account.getAccountNo() == accountNo) {
                if (account.getPin() != pin) {
                    return ("Incorrect PIN");
                }

                Atm atm = bankService.getAtms().get(0);
                int max = 0;
                for (int i=0;i<=account.getOpeningBalance()+account.getOverdraft();i+=5) {
                    max = i;
                    if (i==this.getMaximumAmount()) {
                        break;
                    }
                }
                return "Maximum withdrawal amount is: " + max;
            }
        }
        return ("Account not found");
    }

    public int getMaximumAmount() {
        Atm atm = bankService.getAtms().get(0);
        return (atm.getFifties()*50) + (atm.getTwenties()*20) + (atm.getTens()*10) + (atm.getFives()*5);
    }

    public boolean isValidRequest(int request) {
        Atm atm = bankService.getAtms().get(0);
        int fifties = atm.getFifties();
        int twenties = atm.getTwenties();
        int tens = atm.getTens();
        int fives = atm.getFives();
        if (request%5 !=0) {
            return false;
        }

        if (fives==0 && request%10 !=0) {
            return false;
        }

        if (fives==0 && tens==0 && (request%20 !=0 || request%50 !=0)) {
            return false;
        }

        if (fives==0 && tens==0 && twenties==0 && request%50 !=0) {
            return false;
        }

        return true;
    }

    @GetMapping("/withdraw")
    public String withdrawFunds( @RequestParam("accountNo") int accountNo, @RequestParam("pin") int pin, @RequestParam("amount") int amount) {
        List<Account> accountList = bankService.getAccounts();
        for (Account account: accountList) {
            if (account.getAccountNo() == accountNo) {
                if (account.getPin() != pin) {
                    return ("Incorrect PIN");
                }

                Atm atm = bankService.getAtms().get(0);
                int fifties = atm.getFifties();
                int twenties = atm.getTwenties();
                int tens = atm.getTens();
                int fives = atm.getFives();
                int total = 0;
                int fiftiesDispensed = 0;
                int twentiesDispensed = 0;
                int tensDispensed = 0;
                int fivesDispensed = 0;

                if (amount>this.getMaximumAmount()) {
                    return ("Request is greater than maximum amount available. Please request up to " + this.getMaximumAmount());
                }

                if (!isValidRequest(amount)) {
                    return ("Atm does not have the notes required to fulfill this request");
                }

                if (amount<5) {
                    return ("Invalid amount requested. Please request at least €5.");
                }

                if (amount>(account.getOpeningBalance()+account.getOverdraft())) {
                    return ("Amount requested is greater than your account balance + overdraft. Please request up to " + (account.getOpeningBalance()+account.getOverdraft()));
                }

                while(true) {
                    if (amount>=50 && fifties!=0) {
                        amount -= 50;
                        fifties -= 1;
                        total += 50;
                        fiftiesDispensed += 1;
                    } else if (amount>=20 && twenties!=0) {
                        amount -= 20;
                        twenties -=1;
                        total += 20;
                        twentiesDispensed += 1;
                    } else if (amount>=10 && tens!=0) {
                        amount -= 20;
                        tens -=1;
                        total += 10;
                        tensDispensed += 1;
                    } else if (amount>=5 && fives!=0) {
                        amount -= 20;
                        fives -=1;
                        total += 5;
                        fivesDispensed += 1;
                    }
                    else {
                        break;
                    }

                }
                int remainingBalance = account.getOpeningBalance()-total;
                int notesDispensed =  fiftiesDispensed+twentiesDispensed+tensDispensed+fivesDispensed;
                bankService.setAtm(new Atm(1, fifties, twenties, tens, twenties));
                bankService.setAccount(new Account(account.getId(), account.getAccountNo(), remainingBalance, account.getOverdraft(), account.getPin()));

                return("Dispensed: " + total + ". Total notes dispensed: " + notesDispensed + ". This includes: Fifties: " + fiftiesDispensed +
                        " Twenties: " + twentiesDispensed +
                        " Tens: " + tensDispensed +
                        " Fives: " + fivesDispensed + ". Remaining balance is: " + remainingBalance);
            }
        }
        return ("Account not found");
    }


}
