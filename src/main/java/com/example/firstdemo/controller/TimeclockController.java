package com.example.firstdemo.controller;

import com.example.firstdemo.Exception.SuccessResponse;
import com.example.firstdemo.controller.pojo.AccountDTO;
import com.example.firstdemo.service.AccountService;
import com.example.firstdemo.service.TimeclockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@Slf4j
public class TimeclockController {
    private final TimeclockService timeclocksevice;
    @PostMapping("/Timeclock")
    public ResponseEntity<SuccessResponse> usertimeclock(@AuthenticationPrincipal String username) {
        log.info("開始會員打卡 [帳號:'{}']", username);
        return timeclocksevice.UserTimeclock(username);
    }
}
