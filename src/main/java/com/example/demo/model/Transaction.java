package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Entity
public class Transaction {

    public enum TransactionType {
        DEPOSIT, WITHDRAWAL
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private TransactionType type;
    private double amount;
    private String description;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private BankAccount bankAccount;

    public Transaction(TransactionType transactionType, double amount, String description, BankAccount bankAccount) {
        this.type=transactionType;
        this.amount=amount;
        this.description=description;
        this.bankAccount=bankAccount;
    }

}
