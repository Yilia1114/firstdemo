package com.example.firstdemo.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;

@Entity
@Table(name = "time_clock_statistics")
@Getter
@Setter
public class TimeClockStatistics {
    @Id
    @Column (name = "tc_statistics_id")
    private  long tcStatisticsId;
    @Column (name = "username")
    private String userName;
    @Column (name = "tc_statistics_update_time")
    private Timestamp tcStatisticsUpdateTime;
    @Column (name = "user_time_lag")
    private Long userTimeLag;
}
