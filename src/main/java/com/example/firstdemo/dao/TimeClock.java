package com.example.firstdemo.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
public class TimeClock {
    @Id
    private long time_clock_id;
    private String username;
    private Timestamp user_clock_time;
}
