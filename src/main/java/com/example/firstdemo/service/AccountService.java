package com.example.firstdemo.service;

import com.example.firstdemo.dao.Account;
import com.example.firstdemo.controller.pojo.AccountDTO;
import com.example.firstdemo.dao.Repository.AccountRepository;
import com.example.firstdemo.service.helper.AccountValidationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //註冊帳號
    public ResponseEntity<Account> createAccount(AccountDTO accountDTO) {

            //驗證帳號密碼
            AccountValidationHelper.validateAccount(accountDTO, accountRepository);
            //成功直接註冊
            Account newAccount = new Account();
            newAccount.setId(accountRepository.count()+1);
            newAccount.setUser(accountDTO.getUser());
            newAccount.setPassword(bCryptPasswordEncoder.encode(accountDTO.getPassword()));
            accountRepository.save(newAccount);

            return ResponseEntity.ok(newAccount);
    }


    //取得特定ID的帳號
    public  ResponseEntity<AccountDTO> getAccountById(Long id) {
        Account account = accountRepository.findById(id).orElse(null);
        if (account != null) {
            AccountDTO accountDTO = AccountValidationHelper.convertToDTO(account);
            return ResponseEntity.ok().body(accountDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //更新特定ID的帳號密碼
    public ResponseEntity<String> updateAccount(Long id, AccountDTO accountDTO) {
        Account existingAccount = accountRepository.findById(id).orElse(null);
        if (existingAccount != null) {
            //驗證要修改的帳號密碼
            AccountValidationHelper.validateAccount(accountDTO, accountRepository);

            // 更新帳戶資訊
            existingAccount.setUser(accountDTO.getUser());
            existingAccount.setPassword(bCryptPasswordEncoder.encode(accountDTO.getPassword()));
            accountRepository.save(existingAccount);
            return ResponseEntity.ok("Account updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //刪除特定ID的帳號
    public ResponseEntity<String> deleteAccount(Long id) {
        Account existingAccount = accountRepository.findById(id).orElse(null);
        if (existingAccount != null) {
            accountRepository.delete(existingAccount);
            return ResponseEntity.ok("Account deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }




}
