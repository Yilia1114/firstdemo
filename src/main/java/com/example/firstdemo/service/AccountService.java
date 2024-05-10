package com.example.firstdemo.service;

import com.example.firstdemo.dao.Account;
import com.example.firstdemo.controller.pojo.AccountDTO;
import com.example.firstdemo.dao.Repository.AccountRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private AccountRepository accountRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public ResponseEntity<String> createAccount(AccountDTO accountDTO) {
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

    public  ResponseEntity<AccountDTO> getAccountById(Long id) {
        Account account = accountRepository.findById(id).orElse(null);
        if (account != null) {
            AccountDTO accountDTO = convertToDTO(account);
            return ResponseEntity.ok().body(accountDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<String> updateAccount(Long id, AccountDTO accountDTO) {
        Account existingAccount = accountRepository.findById(id).orElse(null);
        if (existingAccount != null) {
            // 更新帳戶資訊
            existingAccount.setUser(accountDTO.getUser());
            existingAccount.setPassword(accountDTO.getPassword());
            accountRepository.save(existingAccount);
            return ResponseEntity.ok("Account updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<String> deleteAccount(Long id) {
        Account existingAccount = accountRepository.findById(id).orElse(null);
        if (existingAccount != null) {
            accountRepository.delete(existingAccount);
            return ResponseEntity.ok("Account deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Helper 用於轉換 Account 物件為 AccountDTO 物件
    private AccountDTO convertToDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setUser(account.getUser());
        // 不返回密碼信息
        return accountDTO;
    }


}
