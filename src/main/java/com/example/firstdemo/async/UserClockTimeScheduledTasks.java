package com.example.firstdemo.async;

import com.example.firstdemo.dao.JPA.TimeClockRepository;
import com.example.firstdemo.dao.JPA.TimeClockStatisticsRepository;
import com.example.firstdemo.dao.TimeClockStatistics;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserClockTimeScheduledTasks {

    private final TimeClockRepository timeClockRepository;
    private final TimeClockStatisticsRepository timeClockStatisticsRepository;

    @Scheduled(fixedRate = 1800000) // 每30分鐘執行一次
    public void calculateUserClockTime() {
        List<String> users = timeClockRepository.findDistinctUsernames();
        for (String user : users) {
            Timestamp user_earliest_clock_time = timeClockRepository.findListUserClockTimeByUsernameOrderByUserClockTimeAsc(user);
            Timestamp user_lasttime_clock_time = timeClockRepository.findFirstUserClockTimeByUsernameOrderByUserClockTimeDesc(user);

            if (user_earliest_clock_time!=null && user_lasttime_clock_time != null) {
                long earliestTimeInMillis = user_earliest_clock_time.getTime();
                long lastTimeInMillis = user_lasttime_clock_time.getTime();
                long timeDifferenceInMillis = lastTimeInMillis - earliestTimeInMillis;

                // 將時間差轉換為分
                long minutes = timeDifferenceInMillis / 1000 / 60;

                TimeClockStatistics statistics = new TimeClockStatistics();
                statistics.setUsername(user);
                statistics.setTc_statistis_update_time(new Timestamp(System.currentTimeMillis())); // 當前時間
                statistics.setUser_time_lag(minutes);
                statistics.setTc_statistis_id(timeClockStatisticsRepository.count()+1);

                timeClockStatisticsRepository.save(statistics);
            }
        }
    }
}
