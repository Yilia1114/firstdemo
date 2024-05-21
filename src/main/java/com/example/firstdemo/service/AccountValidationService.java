package com.example.firstdemo.service;

import com.example.firstdemo.controller.pojo.AccountDTO;
import com.example.firstdemo.dao.Account;
import com.example.firstdemo.dao.jpa.AccountRepository;
import com.example.firstdemo.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;


@Service
@Slf4j
@RequiredArgsConstructor
public class AccountValidationService {

    private final AccountRepository accountRepository;
    private final MessageSource messageSource;


    public void validateAccount (AccountDTO accountDTO , Locale locale) {
        // 帳號密碼為空
        if(accountDTO.getUsername().isEmpty() || accountDTO.getPassword().isEmpty()){
            String errorMessage = messageSource.getMessage("error.register.empty",null,locale);
            throw  new BusinessException(errorMessage);
        }
        // 帳號重複
        Account existingAccount = accountRepository.findByUsername(accountDTO.getUsername());
        if(existingAccount != null){
            String errorMessage = messageSource.getMessage("error.register.duplicate",null,locale);
            throw  new BusinessException(errorMessage);
        }
        // 帳號建立條件不符
        if(accountDTO.getUsername().matches("^[a-zA-Z0-9]+$")){
            String errorMessage = messageSource.getMessage("error.register.account.case",null,locale);
            throw  new BusinessException(errorMessage);
        }
        //密碼建立條件不符
        if(accountDTO.getPassword().matches("^(?=.*[A-Z])[a-zA-Z0-9]+$")){
            String errorMessage = messageSource.getMessage("error.register.password.case",null,locale);
            throw  new BusinessException(errorMessage);
        }
    }
}