package com.example.atm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public class AtmController {

    private final AtmService atmService;

    @Autowired
    public AtmController(AtmService atmService) {
        this.atmService = atmService;
    }



//    public int getMaximumAmount() {
//        return (fifties*50) + (twenties*20) + (tens*10) + (fives*5);
//    }
//
//    public boolean isValidRequest(int request) {
//        if (request%5 !=0) {
//            return false;
//        }
//
//        if (fives==0 && request%10 !=0) {
//            return false;
//        }
//
//        if (fives==0 && tens==0 && (request%20 !=0 || request%50 !=0)) {
//            return false;
//        }
//
//        if (fives==0 && tens==0 && twenties==0 && request%50 !=0) {
//            return false;
//        }
//
//        return true;
//    }
//
//    public int DispenseFunds(int request) {
//        int total = 0;
//        int fiftiesDispensed = 0;
//        int twentiesDispensed = 0;
//        int tensDispensed = 0;
//        int fivesDispensed = 0;
//
//        if (!isValidRequest(request)) {
//            System.out.println("Atm does not have enough notes to process this request");
//        }
//
//        if (request<0) {
//            System.out.println("Invalid amount requested");
//            return 0;
//        }
//
//        if (request>this.getMaximumAmount()) {
//            System.out.println("Request is greater than maximum amount available. Please request up to " + this.getMaximumAmount());
//            return 0;
//        }
//
//        while(true) {
//            if (request>=50 && fifties!=0) {
//                request -= 50;
//                fifties -= 1;
//                total += 50;
//                fiftiesDispensed += 1;
//            } else if (request>=20 && twenties!=0) {
//                request -= 20;
//                twenties -=1;
//                total += 20;
//                twentiesDispensed += 1;
//            } else if (request>=10 && tens!=0) {
//                request -= 20;
//                tens -=1;
//                total += 10;
//                tensDispensed += 1;
//            } else if (request>=5 && fives!=0) {
//                request -= 20;
//                fives -=1;
//                total += 5;
//                fivesDispensed += 1;
//            }
//            else {
//                break;
//            }
//
//        }
//        System.out.println("Dispensed: " + total);
//        System.out.println("This includes: Fifties: " + fiftiesDispensed +
//                " Twenties: " + twentiesDispensed +
//                " Tens: " + tensDispensed +
//                " Fives: " + fivesDispensed);
//        return total;
//
//    }
}
