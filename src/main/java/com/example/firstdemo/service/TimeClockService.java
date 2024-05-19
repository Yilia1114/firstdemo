package com.example.firstdemo.service;

import com.example.firstdemo.exception.BusinessException;
import com.example.firstdemo.exception.SuccessResponse;
import com.example.firstdemo.dao.Account;
import com.example.firstdemo.dao.jpa.AccountRepository;
import com.example.firstdemo.dao.jpa.TimeClockRepository;
import com.example.firstdemo.dao.TimeClock;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class TimeClockService {
    private final TimeClockRepository timeClockRepository;
    private final AccountRepository accountRepository;
    public ResponseEntity<SuccessResponse> userTimeClock(String username) {
        //確認是否為會員
        log.debug("確認是否為會員");
        Account account = accountRepository.findByUsername(username);

        if (account!=null){
            TimeClock newtimeclock = new TimeClock();
            newtimeclock.setUserName(account.getUsername());
            newtimeclock.setUserClockTime(Timestamp.valueOf(LocalDateTime.now()));
            timeClockRepository.save(newtimeclock);
            log.info("會員打卡成功");
            return ResponseEntity.ok(SuccessResponse.successMessage("打卡成功"));
        }
        else {
            log.error("查無會員資料,建立資料失敗");
            throw new BusinessException("打卡失敗");
        }
    }

    @Transactional
    public ResponseEntity<SuccessResponse> userFailTimeClock(String username) {
        //確認是否為會員
        log.debug("確認是否為會員: {}", username);
        Account account = accountRepository.findByUsername(username);

        if (account!=null){
            TimeClock newtimeclock = new TimeClock();
            newtimeclock.setUserName(account.getUsername());
            newtimeclock.setUserClockTime(Timestamp.valueOf(LocalDateTime.now()));
            timeClockRepository.save(newtimeclock);
            throw new RuntimeException("模擬突發錯誤") ;
        }
        else {
            log.error("查無會員資料, 用戶名: {}", username);
            throw new BusinessException("打卡失敗");
        }

    }

}
