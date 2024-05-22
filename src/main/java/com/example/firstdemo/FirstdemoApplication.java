package com.example.firstdemo;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.example.firstdemo.dao.mybatis") // 指定Mapper接口所在的包
@EnableScheduling
@Slf4j
public class FirstdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstdemoApplication.class, args);
	}
}
