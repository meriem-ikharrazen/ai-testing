package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Setter
@Entity
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "bank")
    private List<BankUser> users;

    @OneToMany(mappedBy = "bank")
    private List<BankAccount> accounts;

    public Bank() {
        this.users = new ArrayList<>();
        this.accounts = new ArrayList<>();
    }

    public void addUser(BankUser user) {
        users.add(user);
        user.setBank(this);
    }

    public void removeUser(BankUser user) {
        users.remove(user);
        user.setBank(null);
    }

    public void addAccount(BankAccount account) {
        accounts.add(account);
        account.setBank(this);
    }

    public void removeAccount(BankAccount account) {
        accounts.remove(account);
        account.setBank(null);
    }

}
