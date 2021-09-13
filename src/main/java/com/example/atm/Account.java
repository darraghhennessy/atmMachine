package com.example.atm;

import javax.persistence.*;

@Entity
@Table
public class Account {

    @Id
    @SequenceGenerator(
            name = "account_sequence",
            sequenceName = "account_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "account_sequence"
    )
    private long id;
    private int accountNo;
    private int pin;
    private int openingBalance;
    private int overdraft;

    public Account() {
    }

    public Account(long id, int accountNo, int pin, int openingBalance, int overdraft) {
        this.id = id;
        this.accountNo = accountNo;
        this.pin = pin;
        this.openingBalance = openingBalance;
        this.overdraft = overdraft;
    }

    public Account(int accountNo, int pin, int openingBalance, int overdraft) {
        this.accountNo = accountNo;
        this.pin = pin;
        this.openingBalance = openingBalance;
        this.overdraft = overdraft;
    }

    public long getId() {
        return id;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public int getPin() {
        return pin;
    }

    public int getOpeningBalance() {
        return openingBalance;
    }

    public int getOverdraft() {
        return overdraft;
    }

}