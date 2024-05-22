package com.example.firstdemo.schedule;

import com.example.firstdemo.async.UserClockTimeTask;
import com.example.firstdemo.dao.jpa.TimeClockRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class UserClockTimeSchedule {

    private final TimeClockRepository timeClockRepository;
    private final UserClockTimeTask userClockTimeTask;
    private static final Logger logger = LoggerFactory.getLogger(UserClockTimeSchedule.class);

    @Scheduled(fixedRate = 360000) //用於測試
    //@Scheduled(cron = "0 0 0 * * ?") // 每天晚上凌晨12點執行一次
    public void calculateUserClockTime() {
        List<String> users = timeClockRepository.findDistinctUsernames();
        List<CompletableFuture<Void>> futures = users.stream()
                .map(userClockTimeTask::processUserClockTime)
                .toList();
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        allFutures.thenRun(() -> logger.info("所有會員打卡時間已計算完成"));

    }

}
