package com.example.firstdemo.service;

import com.example.firstdemo.exception.BusinessException;
import com.example.firstdemo.exception.SuccessResponse;
import com.example.firstdemo.dao.mybatis.AccountMapper;
import com.example.firstdemo.util.JwtUtils;
import com.example.firstdemo.dao.Account;
import com.example.firstdemo.controller.pojo.AccountDTO;
import com.example.firstdemo.dao.jpa.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Locale;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {

    private final AccountRepository accountRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final AccountMapper accountMapper;

    private final MessageSource messageSource;

    private  final  AccountValidationService accountValidationService;


    //註冊帳號
    public ResponseEntity<SuccessResponse> createAccount(AccountDTO accountDTO,Locale locale) {

        //帳號密碼驗證
        accountValidationService.validateAccount(accountDTO,locale);

        //開始註冊
        Account newAccount = new Account();
        newAccount.setUsername(accountDTO.getUsername());
        newAccount.setPassword(bCryptPasswordEncoder.encode(accountDTO.getPassword()));
        accountRepository.save(newAccount);
        return ResponseEntity.ok(SuccessResponse.successMessage(messageSource.getMessage("success.registration", null, locale)));
    }


    //修改帳號密碼
    public ResponseEntity<SuccessResponse> updateAccount(Long id, AccountDTO accountDTO,Locale locale) {
        Account existingAccount = accountRepository.findById(id).orElse(null);

        if (existingAccount != null) {
            //帳號密碼驗證
            accountValidationService.validateAccount(accountDTO,locale);

            // 更新帳戶資訊
            existingAccount.setUsername(accountDTO.getUsername());
            existingAccount.setPassword(bCryptPasswordEncoder.encode(accountDTO.getPassword()));
            accountRepository.save(existingAccount);
            String successMessage = messageSource.getMessage("success.update", null, locale);
            return ResponseEntity.ok(SuccessResponse.successMessage(successMessage));
        } else {
            String errorMessage = messageSource.getMessage("error.update", null, locale);
            throw new BusinessException(errorMessage);
        }
    }


    // 登入
    public ResponseEntity<SuccessResponse> login(AccountDTO accountDTO,Locale locale) {

        // 1. 從資料庫中根據用戶名查詢帳戶資訊，包括加密後的密碼。
        Account account = accountMapper.findByUsername(accountDTO.getUsername());

        // 2. 使用 BCryptPasswordEncoder.matches() 方法來比較用戶輸入的密碼和資料庫中的加密密碼是否匹配。
        if (account != null && bCryptPasswordEncoder.matches(accountDTO.getPassword(), account.getPassword())) {
            // 密碼匹配，登入成功
            // 將userid包到jwt 回傳Token
                String token = JwtUtils.generateToken(account.getUsername());
                return ResponseEntity.ok(SuccessResponse.successToken(token));

        } else {
            // 密碼不匹配，登入失敗
            String errorMessage = messageSource.getMessage("error.login", null, locale);
            throw new BusinessException(errorMessage);
        }

    }
}
