package com.example.firstdemo.async;

import com.example.firstdemo.dao.JPA.TimeClockRepository;
import com.example.firstdemo.dao.JPA.TimeClockStatisticsRepository;
import com.example.firstdemo.dao.TimeClockStatistics;
import com.example.firstdemo.service.helper.AccountValidationHelper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;



@Component
@RequiredArgsConstructor
public class UserClockTimeTask {
    private final TimeClockRepository timeClockRepository;
    private final TimeClockStatisticsRepository timeClockStatisticsRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserClockTimeTask.class);

    @Async("taskExecutor")
    public void processUserClockTime(String user) {
        logger.info("排程:計算打卡時間差 會員:{}",user);
            Timestamp user_earliest_clock_time = timeClockRepository.findListUserClockTimeByUsernameOrderByUserClockTimeAsc(user);
            Timestamp user_lasttime_clock_time = timeClockRepository.findFirstUserClockTimeByUsernameOrderByUserClockTimeDesc(user);

            if (user_earliest_clock_time!=null && user_lasttime_clock_time != null) {

                long earliestTime = user_earliest_clock_time.getTime();
                long lastTime = user_lasttime_clock_time.getTime();
                long timeDifferenceInMillis = lastTime - earliestTime;

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
