package com.example.firstdemo.controller;
import com.example.firstdemo.controller.pojo.AccountDTO;
import com.example.firstdemo.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/register")
    public ResponseEntity<String> registerAccount(@RequestBody AccountDTO accountDTO) {
        return registrationService.register(accountDTO);
    }
}
