package com.example.firstdemo.dao.jpa;
import com.example.firstdemo.dao.TimeClockStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeClockStatisticsRepository extends JpaRepository<TimeClockStatistics, Long> {}
