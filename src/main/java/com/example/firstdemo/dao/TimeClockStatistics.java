package com.example.firstdemo.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Locale;

@Entity
@Getter
@Setter
public class TimeClockStatistics {
    @Id
    private  long tc_statistis_id;
    private String username;
    private Timestamp tc_statistis_update_time;
    private Long user_time_lag;

}
