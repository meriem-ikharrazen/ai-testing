package com.example.demo.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity
public class SavingsAccount extends BankAccount {
    private double interestRate;

    public SavingsAccount(BankUser user, double interestRate) {
        super(user);
        this.interestRate = interestRate;
    }
}
