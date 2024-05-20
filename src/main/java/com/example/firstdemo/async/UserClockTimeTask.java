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
    public CompletableFuture<Void> processUserClockTime(String username) {
        Timestamp userEarliestClockTime = timeClockRepository.findListUserClockTimeByUsernameOrderByUserClockTimeAsc(username);
        Timestamp userLattimeClockTime = timeClockRepository.findFirstUserClockTimeByUsernameOrderByUserClockTimeDesc(username);
        log.info("會員{} 開始計算時間差", username);
        if (userEarliestClockTime != null && userLattimeClockTime != null) {

            long earliestTime = userEarliestClockTime.getTime();
            long lastTime = userLattimeClockTime.getTime();
            long timeDifferenceInMillis = lastTime - earliestTime;

            // 將時間差轉換為分
            long minutes = timeDifferenceInMillis / 1000 / 60;

            TimeClockStatistics statistics = new TimeClockStatistics();
            statistics.setUserName(username);
            statistics.setTcStatisticsUpdateTime(new Timestamp(System.currentTimeMillis())); // 當前時間
            statistics.setUserTimeLag(minutes);
            timeClockStatisticsRepository.save(statistics);
            log.debug("會員{} 時間差計算完畢", username);
        }
        return null;
    }
}
