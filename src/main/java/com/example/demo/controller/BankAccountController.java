package com.example.demo.controller;

import com.example.demo.model.BankAccount;
import com.example.demo.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bankaccounts")
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @GetMapping
    public List<BankAccount> getAllBankAccounts() {
        return bankAccountService.getAllBankAccounts();
    }

    @GetMapping("/{id}")
    public BankAccount getBankAccountById(@PathVariable Long id) {
        return bankAccountService.getBankAccountById(id);
    }

    @PostMapping
    public BankAccount createBankAccount(@RequestBody BankAccount bankAccount) {
        return bankAccountService.saveBankAccount(bankAccount);
    }

    @DeleteMapping("/{id}")
    public void deleteBankAccount(@PathVariable Long id) {
        bankAccountService.deleteBankAccount(id);
    }

    @PostMapping("/{id}/deposit")
    public BankAccount deposit(@PathVariable Long id, @RequestParam double amount, @RequestParam String description) {
        return bankAccountService.deposit(id, amount, description);
    }

    @PostMapping("/{id}/withdraw")
    public BankAccount withdraw(@PathVariable Long id, @RequestParam double amount, @RequestParam String description) {
        return bankAccountService.withdraw(id, amount, description);
    }
}
