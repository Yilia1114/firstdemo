package com.example.firstdemo.dao.JPA;
import com.example.firstdemo.dao.TimeClock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface TimeClockRepository extends JpaRepository<TimeClock, Long> {
    TimeClock findByUsername(String user);
}
