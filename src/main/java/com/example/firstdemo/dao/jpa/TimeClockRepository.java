package com.example.firstdemo.dao.jpa;
import com.example.firstdemo.dao.TimeClock;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface TimeClockRepository extends JpaRepository<TimeClock, Long> {
    @Query(value = "SELECT DISTINCT username FROM time_clock",nativeQuery = true)
    List<String> findDistinctUsernames();

    @Query(value = "select user_clock_time from time_clock tc where username = :username and DATE(user_clock_time) = CURDATE() order by user_clock_time desc LIMIT 1 ;",nativeQuery = true)
    Timestamp  findFirstUserClockTimeByUsernameOrderByUserClockTimeDesc(@Param("username")String username);

    @Query(value = "select user_clock_time from time_clock tc where username = :username and DATE(user_clock_time) = CURDATE() order by user_clock_time asc LIMIT 1 ;",nativeQuery = true)
    Timestamp  findListUserClockTimeByUsernameOrderByUserClockTimeAsc(@Param("username")String username);

}
