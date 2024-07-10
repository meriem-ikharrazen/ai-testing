package com.example.demo.service;

import com.example.demo.model.BankAccount;
import com.example.demo.model.Transaction;
import com.example.demo.model.Transaction.TransactionType;
import com.example.demo.repository.BankAccountRepository;
import com.example.demo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public List<BankAccount> getAllBankAccounts() {
        return bankAccountRepository.findAll();
    }

    public BankAccount getBankAccountById(Long id) {
        return bankAccountRepository.findById(id).orElse(null);
    }

    public BankAccount saveBankAccount(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }

    public void deleteBankAccount(Long id) {
        bankAccountRepository.deleteById(id);
    }

    public BankAccount deposit(Long accountId, double amount, String description) {
        Optional<BankAccount> optionalBankAccount = bankAccountRepository.findById(accountId);

        if (optionalBankAccount.isPresent()) {
            BankAccount bankAccount = optionalBankAccount.get();
            bankAccount.setBalance(bankAccount.getBalance() + amount);

            Transaction transaction = new Transaction(TransactionType.DEPOSIT, amount, description, bankAccount);
            transactionRepository.save(transaction);

            return bankAccountRepository.save(bankAccount);
        }

        throw new RuntimeException("Bank account not found");
    }

    public BankAccount withdraw(Long accountId, double amount, String description) {
        Optional<BankAccount> optionalBankAccount = bankAccountRepository.findById(accountId);

        if (optionalBankAccount.isPresent()) {
            BankAccount bankAccount = optionalBankAccount.get();

            if (bankAccount.getBalance() >= amount) {
                bankAccount.setBalance(bankAccount.getBalance() - amount);

                Transaction transaction = new Transaction(TransactionType.WITHDRAWAL, amount, description, bankAccount);
                transactionRepository.save(transaction);

                return bankAccountRepository.save(bankAccount);
            }

            throw new RuntimeException("Insufficient funds");
        }

        throw new RuntimeException("Bank account not found");
    }
}
