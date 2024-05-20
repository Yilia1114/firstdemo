package com.example.firstdemo;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Locale;

@SpringBootApplication
@MapperScan("com.example.firstdemo.dao.MyBatis") // 指定Mapper接口所在的包
@EnableScheduling
@Slf4j
public class FirstdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstdemoApplication.class, args);
	}

	@PostConstruct
	public void init() {
		// 设置JVM默认区域设置为中文繁体（zh_TW）
		Locale.setDefault(Locale.TAIWAN);
		log.debug("JVM default locale set to: " + Locale.getDefault());
	}

}
