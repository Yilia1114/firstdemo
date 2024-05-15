package com.example.firstdemo.controller;
import com.example.firstdemo.Exception.SuccessResponse;
import com.example.firstdemo.controller.pojo.AccountDTO;
import com.example.firstdemo.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class AccountController {

    private final AccountService accountService;


    @GetMapping("/home")
    public String home() {
        return "系統首頁";
    }

    //註冊
    @PostMapping("/account")
    public ResponseEntity<SuccessResponse> createAccount(@RequestBody AccountDTO accountDTO) {
        return accountService.createAccount(accountDTO);
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<SuccessResponse> getAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }
    //修改帳號密碼
    @PutMapping("/account/{id}")
    public ResponseEntity<SuccessResponse> updateAccount(@PathVariable Long id, @RequestBody AccountDTO accountDTO) {
        return accountService.updateAccount(id, accountDTO);
    }

    @DeleteMapping("/account/{id}")
    public ResponseEntity<SuccessResponse> deleteAccount(@PathVariable Long id) {
        return accountService.deleteAccount(id);
    }


    //登入
    @PostMapping("/login")
    public ResponseEntity<SuccessResponse> login(@RequestBody AccountDTO accountDTO ) {
        Locale locale = LocaleContextHolder.getLocale();
        return accountService.login(accountDTO,locale);
    }


}
