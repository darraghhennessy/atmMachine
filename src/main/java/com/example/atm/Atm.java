package com.example.atm;

import javax.persistence.*;

@Entity
@Table
public class Atm {

    @Id
    @SequenceGenerator(
            name = "atm_sequence",
            sequenceName = "atm_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "atm_sequence"
    )

    private long id;
    private int fifties;
    private int twenties;
    private int tens;
    private int fives;

    public Atm() {
    }

    public Atm(int fifties, int twenties, int tens, int fives) {
        this.fifties = fifties;
        this.twenties = twenties;
        this.tens = tens;
        this.fives = fives;
    }

    public Atm(long id, int fifties, int twenties, int tens, int fives) {
        this.id = id;
        this.fifties = fifties;
        this.twenties = twenties;
        this.tens = tens;
        this.fives = fives;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getFifties() {
        return fifties;
    }

    public void setFifties(int fifties) {
        this.fifties = fifties;
    }

    public int getTwenties() {
        return twenties;
    }

    public void setTwenties(int twenties) {
        this.twenties = twenties;
    }

    public int getTens() {
        return tens;
    }

    public void setTens(int tens) {
        this.tens = tens;
    }

    public int getFives() {
        return fives;
    }

    public void setFives(int fives) {
        this.fives = fives;
    }

    @Override
    public String toString() {
        return "Atm{" +
                "id=" + id +
                ", fifties=" + fifties +
                ", twenties=" + twenties +
                ", tens=" + tens +
                ", fives=" + fives +
                '}';
    }
}
