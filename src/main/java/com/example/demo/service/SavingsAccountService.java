package com.example.demo.service;

import com.example.demo.model.SavingsAccount;
import com.example.demo.repository.SavingsAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SavingsAccountService {

    @Autowired
    private SavingsAccountRepository savingsAccountRepository;

    public List<SavingsAccount> getAllSavingsAccounts() {
        return savingsAccountRepository.findAll();
    }

    public SavingsAccount getSavingsAccountById(Long id) {
        return savingsAccountRepository.findById(id).orElse(null);
    }

    public SavingsAccount saveSavingsAccount(SavingsAccount savingsAccount) {
        return savingsAccountRepository.save(savingsAccount);
    }

    public void deleteSavingsAccount(Long id) {
        savingsAccountRepository.deleteById(id);
    }

    public void applyInterest() {
        List<SavingsAccount> savingsAccounts = savingsAccountRepository.findAll();
        for (SavingsAccount account : savingsAccounts) {
            double interest = account.getBalance() * account.getInterestRate();
            account.setBalance(account.getBalance() + interest);
            savingsAccountRepository.save(account);
        }
    }
}
