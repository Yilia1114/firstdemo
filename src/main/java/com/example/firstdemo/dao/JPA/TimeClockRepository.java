package com.example.firstdemo.dao.JPA;
import com.example.firstdemo.dao.TimeClock;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public interface TimeClockRepository extends JpaRepository<TimeClock, Long> {
    @Query(value = "SELECT DISTINCT username FROM time_clock",nativeQuery = true)
    List<String> findDistinctUsernames();

    @Query(value = "select user_clock_time from time_clock tc where username = :username and DATE(user_clock_time) = CURDATE() order by user_clock_time desc LIMIT 1 ;",nativeQuery = true)
    Timestamp  findFirstUserClockTimeByUsernameOrderByUserClockTimeDesc(@Param("username")String username);

    @Query(value = "select user_clock_time from time_clock tc where username = :username and DATE(user_clock_time) = CURDATE() order by user_clock_time asc LIMIT 1 ;",nativeQuery = true)
    Timestamp  findListUserClockTimeByUsernameOrderByUserClockTimeAsc(@Param("username")String username);

    @Query(value ="select Count(user_clock_time) from time_clock where username =:username AND DATE(user_clock_time) = CURDATE() ;",nativeQuery = true)
    int  countTodayClockInsByUsername(@Param("username")String username);

}
