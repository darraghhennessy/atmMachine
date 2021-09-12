package com.example.atm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AtmController {

    private final AtmService atmService;

    @Autowired
    public AtmController(AtmService atmService) {
        this.atmService = atmService;
    }



    @GetMapping("/atm/max")
    public int getMaximumAmount() {
        Atm atm = atmService.getAtm().get(0);
        return (atm.getFifties()*50) + (atm.getTwenties()*20) + (atm.getTens()*10) + (atm.getFives()*5);
    }

    public boolean isValidRequest(int request) {
        Atm atm = atmService.getAtm().get(0);
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

    @GetMapping("/atm/dispense/{request}")
    public int DispenseFunds(@PathVariable int request) {
        Atm atm = atmService.getAtm().get(0);
        int fifties = atm.getFifties();
        int twenties = atm.getTwenties();
        int tens = atm.getTens();
        int fives = atm.getFives();
        int total = 0;
        int fiftiesDispensed = 0;
        int twentiesDispensed = 0;
        int tensDispensed = 0;
        int fivesDispensed = 0;

        if (!isValidRequest(request)) {
            System.out.println("Atm does not have enough notes to process this request");
            return 0;
        }

        if (request<0) {
            System.out.println("Invalid amount requested");
            return 0;
        }

        if (request>this.getMaximumAmount()) {
            System.out.println("Request is greater than maximum amount available. Please request up to " + this.getMaximumAmount());
            return 0;
        }

        while(true) {
            if (request>=50 && fifties!=0) {
                request -= 50;
                fifties -= 1;
                total += 50;
                fiftiesDispensed += 1;
            } else if (request>=20 && twenties!=0) {
                request -= 20;
                twenties -=1;
                total += 20;
                twentiesDispensed += 1;
            } else if (request>=10 && tens!=0) {
                request -= 20;
                tens -=1;
                total += 10;
                tensDispensed += 1;
            } else if (request>=5 && fives!=0) {
                request -= 20;
                fives -=1;
                total += 5;
                fivesDispensed += 1;
            }
            else {
                break;
            }

        }
        System.out.println("Dispensed: " + total);
        System.out.println("This includes: Fifties: " + fiftiesDispensed +
                " Twenties: " + twentiesDispensed +
                " Tens: " + tensDispensed +
                " Fives: " + fivesDispensed);

        atmService.setAtm(new Atm(1, fifties, twenties, tens, twenties));

        return total;

    }
}
