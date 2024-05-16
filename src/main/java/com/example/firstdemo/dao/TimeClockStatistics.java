package com.example.firstdemo.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import java.sql.Time;

@Entity
@Getter
@Setter
public class TimeClockStatistics {
    @Id
    private  long tc_statistis_id;
    private String username;
    private Time tc_statistis_update_time;
    private Time user_time_lag;

}
