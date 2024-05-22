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
    //打卡新增
    @PostMapping("/time_clocks ")
    public ResponseEntity<SuccessResponse> userTimeClock(@AuthenticationPrincipal String username) {
        log.info("執行: 會員打卡 [帳號:'{}']", username);
        return timeClockService.userTimeClock(username);
    }
    //補卡
    @PutMapping ("/time_clocks/{id}")
    public ResponseEntity<SuccessResponse> updateTimeClock(@PathVariable Long id,@AuthenticationPrincipal String username,@) {
        log.info("執行: 會員補卡 [帳號:'{}']", username);
        return timeClockService.updateTimeClock(id,username);
    }
    //打卡失敗
    @PostMapping("/fail_time_clocks")
    public ResponseEntity<SuccessResponse> failTimeClock(@AuthenticationPrincipal String username) {
        log.info("執行: 打卡失敗");
        return timeClockService.userFailTimeClock(username);
    }
}
