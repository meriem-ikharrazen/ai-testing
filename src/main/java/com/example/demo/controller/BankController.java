package com.example.demo.controller;

import com.example.demo.model.Bank;
import com.example.demo.model.BankAccount;
import com.example.demo.model.SavingsAccount;
import com.example.demo.model.BankUser;
import com.example.demo.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/banks")
public class BankController {

    @Autowired
    private BankService bankService;

    @PostMapping("/bank")
    public Bank addBank( @RequestBody Bank bank) {
        return bankService.saveBank(bank);
    }

    @PostMapping("/{bankId}/users")
    public BankUser addUser(@PathVariable Long bankId, @RequestBody BankUser user) {
        return bankService.addUser(bankId, user);
    }

    @PostMapping("/users/{userId}/accounts")
    public BankAccount addBankAccount(@PathVariable Long userId, @RequestBody BankAccount bankAccount) {
        return bankService.addBankAccount(userId, bankAccount);
    }

    @PostMapping("/users/{userId}/savingsaccounts")
    public SavingsAccount addSavingsAccount(@PathVariable Long userId, @RequestBody SavingsAccount savingsAccount) {
        return bankService.addSavingsAccount(userId, savingsAccount);
    }

    @GetMapping("/users/{userId}/accounts")
    public List<BankAccount> getAccountsForUser(@PathVariable Long userId) {
        return bankService.getAccountsForUser(userId);
    }
}
