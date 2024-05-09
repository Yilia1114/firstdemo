package com.example.firstdemo.service;

import com.example.firstdemo.dao.Account;
import com.example.firstdemo.controller.pojo.AccountDTO;
import com.example.firstdemo.dao.Repository.AccountRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public ResponseEntity<String> register(AccountDTO accountDTO) {
        // 檢查帳號密碼是否為空
        if (StringUtils.isEmpty(accountDTO.getUser()) || StringUtils.isEmpty(accountDTO.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username or password cannot be empty");
        }

        // 檢查帳號是否已存在
        Account existingAccount = accountRepository.findByUser(accountDTO.getUser());
        if (existingAccount != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists");
        }


        Account newAccount = new Account();
        newAccount.setUser(accountDTO.getUser());
        newAccount.setPassword(bCryptPasswordEncoder.encode(accountDTO.getPassword()));
        accountRepository.save(newAccount);

        return ResponseEntity.status(HttpStatus.OK).body("User registered successfully");
    }
}
