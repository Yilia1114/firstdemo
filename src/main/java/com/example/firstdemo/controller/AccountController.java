package com.example.firstdemo.controller;
import com.example.firstdemo.exception.SuccessResponse;
import com.example.firstdemo.controller.pojo.AccountDTO;
import com.example.firstdemo.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Locale;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@Slf4j
public class AccountController {

    private final AccountService accountService;
    @GetMapping("/home")
    public String home() {
        return "系統首頁";
    }

    //註冊
    @PostMapping("/account")
    public ResponseEntity<SuccessResponse> createAccount(@RequestBody AccountDTO accountDTO,@RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        log.info("開始註冊會員 [會員:'{}']",accountDTO.getUsername());
        return accountService.createAccount(accountDTO, locale);
    }

    //修改帳號密碼
    @PutMapping("/account/{id}")
    public ResponseEntity<SuccessResponse> updateAccount(@PathVariable Long id, @RequestBody AccountDTO accountDTO,@RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        log.info("開始修改帳號密碼 [會員:'{}']", accountDTO.getUsername());
        return accountService.updateAccount(id, accountDTO,locale);
    }

    @PostMapping("/login")
    public ResponseEntity<SuccessResponse> login(@RequestBody AccountDTO accountDTO,
                                                 @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        log.info("開始會員登入並取得Token [會員: '{}']",accountDTO.getUsername());
        return accountService.login(accountDTO, locale);
    }


}
