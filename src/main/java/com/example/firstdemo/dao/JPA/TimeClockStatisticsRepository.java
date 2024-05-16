package com.example.firstdemo.dao.JPA;
import com.example.firstdemo.dao.TimeClock;
import com.example.firstdemo.dao.TimeClockStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface TimeClockStatisticsRepository {
    TimeClockStatistics findByUsername(String user);
}
