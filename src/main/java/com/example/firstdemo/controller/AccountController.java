package com.example.firstdemo.controller;
import com.example.firstdemo.AuthResponse;
import com.example.firstdemo.controller.pojo.AccountDTO;
import com.example.firstdemo.dao.Account;
import com.example.firstdemo.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Account> createAccount(@RequestBody AccountDTO accountDTO) {
        return accountService.createAccount(accountDTO);
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }

    @PutMapping("/account/{id}")
    public ResponseEntity<String> updateAccount(@PathVariable Long id, @RequestBody AccountDTO accountDTO) {
        return accountService.updateAccount(id, accountDTO);
    }

    @DeleteMapping("/account/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        return accountService.deleteAccount(id);
    }


    //登入
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AccountDTO accountDTO) {
        return accountService.login(accountDTO);
    }


}
