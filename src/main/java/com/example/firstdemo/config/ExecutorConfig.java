package com.example.firstdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
public class ExecutorConfig {
    @Bean(name = "taskExecutor")
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 核心線程數8
        executor.setCorePoolSize(8);
        // 最大線程數20
        executor.setMaxPoolSize(20);
        // 緩衝隊列200
        executor.setQueueCapacity(200);
        //設定關機時是否等待排程任務完成
        executor.setWaitForTasksToCompleteOnShutdown(true);
        // 設置此執行程序在關閉時應阻止的最大秒數
        executor.setAwaitTerminationSeconds(60);
        // 線程池名的前綴
        executor.setThreadNamePrefix("AsyncTask-");
        // 線程池對拒絕任務的處理策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }
}
