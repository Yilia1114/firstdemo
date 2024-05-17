package com.example.firstdemo.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;

@Table(name = "time_clock")
@Entity
@Getter
@Setter
public class TimeClock {
    @Id
    @Column(name = "time_clock_id")
    private long timeClockId;
    @Column(name = "username")
    private String userName;
    @Column(name = "user_clock_time")
    private Timestamp userClockTime;
}
