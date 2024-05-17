package com.example.firstdemo.schedule;

import com.example.firstdemo.async.UserClockTimeTask;
import com.example.firstdemo.dao.JPA.TimeClockRepository;
import com.example.firstdemo.dao.JPA.TimeClockStatisticsRepository;
import com.example.firstdemo.service.helper.AccountValidationHelper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserClockTimeSchedule {

    private final TimeClockRepository timeClockRepository;
    private final TimeClockStatisticsRepository timeClockStatisticsRepository;
    private final UserClockTimeTask userClockTimeTasks;
    private static final Logger logger = LoggerFactory.getLogger(UserClockTimeSchedule.class);

    @Scheduled(fixedRate = 1800000)
    //@Scheduled(cron = "0 0 0 * * ?") // 每天晚上凌晨12點執行一次
    public void calculateUserClockTime() {
        logger.info("排程:計算打卡時間差 : 開始執行");
        List<String> users = timeClockRepository.findDistinctUsernames();
        for (String user : users) {
            userClockTimeTasks.processUserClockTime(user);
        }
        logger.info("排程:計算打卡時間差 : 結束");
    }

}
