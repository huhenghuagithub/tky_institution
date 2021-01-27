package com.institution.transfer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 创建多线程连接池
 */
@Configuration
@EnableAsync
public class ThreadPoolConfig {

    @Bean("TaskExecutor")
    public ThreadPoolTaskExecutor threadPoolExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(6);
        threadPoolTaskExecutor.setMaxPoolSize(12);
        threadPoolTaskExecutor.setKeepAliveSeconds(60);
        threadPoolTaskExecutor.setQueueCapacity(12);
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        threadPoolTaskExecutor.setAllowCoreThreadTimeOut(true);
        threadPoolTaskExecutor.setThreadNamePrefix("Invoke-");
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }
}
