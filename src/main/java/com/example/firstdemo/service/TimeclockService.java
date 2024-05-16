package com.example.firstdemo.service;

import com.example.firstdemo.Exception.BusinessException;
import com.example.firstdemo.Exception.SuccessResponse;
import com.example.firstdemo.controller.pojo.AccountDTO;
import com.example.firstdemo.dao.Account;
import com.example.firstdemo.dao.JPA.AccountRepository;
import com.example.firstdemo.dao.JPA.TimeClockRepository;
import com.example.firstdemo.dao.TimeClock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Locale;

@Service
@RequiredArgsConstructor
@Slf4j
public class TimeclockService {
    private final TimeClockRepository timeClockRepository;
    private final AccountRepository accountRepository;
    public ResponseEntity<SuccessResponse> UserTimeclock(String username) {
        //確認是否為會員
        log.info("確認是否為會員");
        Account account = accountRepository.findByUsername(username);

        if (account!=null){
            log.info("確認為會員，開始建立會員打卡資料");
            TimeClock newtimeclock = new TimeClock();
            newtimeclock.setTime_clock_id(timeClockRepository.count()+1);
            newtimeclock.setUsername(account.getUsername());
            newtimeclock.setUser_clock_time(Timestamp.valueOf(LocalDateTime.now()));
            timeClockRepository.save(newtimeclock);
            return ResponseEntity.ok(SuccessResponse.successMessage("打卡成功"));
        }
        else {
            log.error("查無會員資料,建立資料失敗");
            throw new BusinessException("打卡失敗");
        }
    }

}
