package com.example.demo.service;

import com.example.demo.model.Bank;
import com.example.demo.model.BankAccount;
import com.example.demo.model.SavingsAccount;
import com.example.demo.model.BankUser;
import com.example.demo.repository.BankRepository;
import com.example.demo.repository.BankAccountRepository;
import com.example.demo.repository.SavingsAccountRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankService {

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private SavingsAccountRepository savingsAccountRepository;

    public List<Bank> getAllBanks() {
        return bankRepository.findAll();
    }

    public Bank getBankById(Long id) {
        return bankRepository.findById(id).orElse(null);
    }

    public Bank saveBank(Bank bank) {
        return bankRepository.save(bank);
    }

    public void deleteBank(Long id) {
        bankRepository.deleteById(id);
    }

    public BankUser addUser(Long bankId, BankUser user) {
        Optional<Bank> optionalBank = bankRepository.findById(bankId);
        if (optionalBank.isPresent()) {
            Bank bank = optionalBank.get();
            userRepository.save(user);
            bank.getUsers().add(user);
            bankRepository.save(bank);
            return user;
        }
        throw new RuntimeException("Bank not found");
    }

    public BankAccount addBankAccount(Long userId, BankAccount bankAccount) {
        Optional<BankUser> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            BankUser user = optionalUser.get();
            bankAccount.setUser(user);
            return bankAccountRepository.save(bankAccount);
        }
        throw new RuntimeException("User not found");
    }

    public SavingsAccount addSavingsAccount(Long userId, SavingsAccount savingsAccount) {
        Optional<BankUser> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            BankUser user = optionalUser.get();
            savingsAccount.setUser(user);
            return savingsAccountRepository.save(savingsAccount);
        }
        throw new RuntimeException("User not found");
    }

    public List<BankAccount> getAccountsForUser(Long userId) {
        Optional<BankUser> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            return bankAccountRepository.findByUserId(userId);
        }
        throw new RuntimeException("User not found");
    }
}
