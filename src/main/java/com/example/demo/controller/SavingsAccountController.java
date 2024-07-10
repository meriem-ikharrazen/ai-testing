package com.example.demo.controller;

import com.example.demo.model.SavingsAccount;
import com.example.demo.service.SavingsAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/savingsaccounts")
public class SavingsAccountController {

    @Autowired
    private SavingsAccountService savingsAccountService;

    @GetMapping
    public List<SavingsAccount> getAllSavingsAccounts() {
        return savingsAccountService.getAllSavingsAccounts();
    }

    @GetMapping("/{id}")
    public SavingsAccount getSavingsAccountById(@PathVariable Long id) {
        return savingsAccountService.getSavingsAccountById(id);
    }

    @PostMapping
    public SavingsAccount createSavingsAccount(@RequestBody SavingsAccount savingsAccount) {
        return savingsAccountService.saveSavingsAccount(savingsAccount);
    }

    @DeleteMapping("/{id}")
    public void deleteSavingsAccount(@PathVariable Long id) {
        savingsAccountService.deleteSavingsAccount(id);
    }

    @PostMapping("/applyInterest")
    public void applyInterest() {
        savingsAccountService.applyInterest();
    }
}
