package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "bank_user")
public class BankUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;

    @ManyToOne
    @JoinColumn(name = "bank_id",nullable = false)
    private Bank bank;

    public BankUser() {
    }

    public BankUser(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
