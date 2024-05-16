package com.example.firstdemo.service;

import com.example.firstdemo.Exception.BusinessException;
import com.example.firstdemo.Exception.SuccessResponse;
import com.example.firstdemo.dao.MyBatis.AccountMapper;
import com.example.firstdemo.util.JwtUtils;
import com.example.firstdemo.dao.Account;
import com.example.firstdemo.controller.pojo.AccountDTO;
import com.example.firstdemo.dao.JPA.AccountRepository;
import com.example.firstdemo.service.helper.AccountValidationHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final JwtUtils jwtUtils;

    private final AccountMapper accountMapper;

    private final MessageSource messageSource;


    //註冊帳號
    public ResponseEntity<SuccessResponse> createAccount(AccountDTO accountDTO,Locale locale) {

        //驗證帳號密碼
        String Code =  AccountValidationHelper.validateAccount(accountDTO, accountRepository);
        if(Code != null){
            String errorMessage = messageSource.getMessage(Code, null, locale);
            throw new BusinessException(errorMessage);
        }

        //成功直接註冊
        Account newAccount = new Account();
        newAccount.setId(accountRepository.count() + 1);
        newAccount.setUsername(accountDTO.getUsername());
        newAccount.setPassword(bCryptPasswordEncoder.encode(accountDTO.getPassword()));
        accountRepository.save(newAccount);

        return ResponseEntity.ok(SuccessResponse.successMessage("註冊成功"));
    }


    //取得特定ID的帳號
    public ResponseEntity<SuccessResponse> getAccountById(Long id) {
        Account account = accountRepository.findById(id).orElse(null);
        //密碼不直接顯示回JSON
        account.setPassword(null);

        if (account != null) {
            return ResponseEntity.ok(SuccessResponse.successWithData("註冊成功",account));
        } else {
            throw new BusinessException("取得失敗");
        }
    }

    //更新特定ID的帳號密碼
    public ResponseEntity<SuccessResponse> updateAccount(Long id, AccountDTO accountDTO,Locale locale) {
        Account existingAccount = accountRepository.findById(id).orElse(null);

        if (existingAccount != null) {
            //驗證要修改的帳號密碼
            String Code = AccountValidationHelper.validateAccount(accountDTO, accountRepository);
            if(Code != null){
                String errorMessage = messageSource.getMessage(Code, null, locale);
                throw new BusinessException(errorMessage);
            }

            // 更新帳戶資訊
            existingAccount.setUsername(accountDTO.getUsername());
            existingAccount.setPassword(bCryptPasswordEncoder.encode(accountDTO.getPassword()));
            accountRepository.save(existingAccount);
            return ResponseEntity.ok(SuccessResponse.successMessage("更新成功"));
        } else {
            throw new BusinessException("更新失敗");
        }
    }

    //刪除特定ID的帳號
    public ResponseEntity<SuccessResponse> deleteAccount(Long id) {
        Account existingAccount = accountRepository.findById(id).orElse(null);
        if (existingAccount != null) {
            accountRepository.delete(existingAccount);
            return ResponseEntity.ok(SuccessResponse.successMessage("刪除成功"));
        } else {
            throw new BusinessException("刪除失敗");
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
                String token = jwtUtils.generateToken(account.getUsername());
                return ResponseEntity.ok(SuccessResponse.successToken(token));

        } else {
            // 密碼不匹配，登入失敗
            String errorMessage = messageSource.getMessage("error.login", null, locale);
            throw new BusinessException(errorMessage);
        }

    }
}
