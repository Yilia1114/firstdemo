package com.example.firstdemo.service;

import com.example.firstdemo.Exception.BusinessException;
import com.example.firstdemo.Exception.SuccessResponse;
import com.example.firstdemo.dao.Account;
import com.example.firstdemo.dao.jpa.AccountRepository;
import com.example.firstdemo.dao.jpa.TimeClockRepository;
import com.example.firstdemo.dao.TimeClock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class TimeclockService {
    private final TimeClockRepository timeClockRepository;
    private final AccountRepository accountRepository;
    public ResponseEntity<SuccessResponse> userTimeClock(String username) {
        //確認是否為會員
        log.info("確認是否為會員");
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

}
