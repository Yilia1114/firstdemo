package com.example.firstdemo.async;

import com.example.firstdemo.dao.jpa.TimeClockRepository;
import com.example.firstdemo.dao.jpa.TimeClockStatisticsRepository;
import com.example.firstdemo.dao.TimeClockStatistics;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.concurrent.CompletableFuture;


@Component
@RequiredArgsConstructor
@Slf4j
public class UserClockTimeTask {
    private final TimeClockRepository timeClockRepository;
    private final TimeClockStatisticsRepository timeClockStatisticsRepository;

    @Async
    public CompletableFuture<Void> processUserClockTime(String user) {
        Timestamp userEarliestClockTime = timeClockRepository.findListUserClockTimeByUsernameOrderByUserClockTimeAsc(user);
        Timestamp userLattimeClockTime = timeClockRepository.findFirstUserClockTimeByUsernameOrderByUserClockTimeDesc(user);

        if (userEarliestClockTime != null && userLattimeClockTime != null) {

            long earliestTime = userEarliestClockTime.getTime();
            long lastTime = userLattimeClockTime.getTime();
            long timeDifferenceInMillis = lastTime - earliestTime;

            // 將時間差轉換為分
            long minutes = timeDifferenceInMillis / 1000 / 60;

            TimeClockStatistics statistics = new TimeClockStatistics();
            statistics.setUserName(user);
            statistics.setTcStatisticsUpdateTime(new Timestamp(System.currentTimeMillis())); // 當前時間
            statistics.setUserTimeLag(minutes);
            timeClockStatisticsRepository.save(statistics);
        }
        return null;
    }
}
