package com.example.demo.service;

import com.example.demo.model.BankUser;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<BankUser> getAllUsers() {
        return userRepository.findAll();
    }

    public BankUser getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public BankUser saveUser(BankUser user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
