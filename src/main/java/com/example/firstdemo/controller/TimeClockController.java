package com.example.firstdemo.controller;

import com.example.firstdemo.exception.SuccessResponse;
import com.example.firstdemo.service.TimeClockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@Slf4j
public class TimeClockController {
    private final TimeClockService timeClockService;
    @PostMapping("/TimeClock")
    public ResponseEntity<SuccessResponse> userTimeClock(@AuthenticationPrincipal String username) {
        log.info("開始會員打卡 [帳號:'{}']", username);
        return timeClockService.userTimeClock(username);
    }
    @PostMapping("/FailTimeClock/{username}")
    public ResponseEntity<SuccessResponse> failTimeClock(@PathVariable  String username) {
        log.info("開始會員打卡 [帳號:'{}']", username);
        return timeClockService.userFailTimeClock(username);
    }
}
