package com.vaccine.availability.config;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
@Configuration
public class ThreadPoolConfiguration {
	
	@Bean(name="threadPoolTaskExecutor")
	public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
		threadPoolTaskExecutor.setCorePoolSize(3);
		threadPoolTaskExecutor.setMaxPoolSize(8);
		threadPoolTaskExecutor.setQueueCapacity(500);
		threadPoolTaskExecutor.setThreadNamePrefix("TaskExecutor");
		threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
		threadPoolTaskExecutor.initialize();
		return threadPoolTaskExecutor;
	}
	
	@Bean(name="threadPoolExecutor")
	public ExecutorService threadPoolExecutor() {
		int corePoolSize = 3;
		int maxPoolSize = 8;
		Long keepAliveTime = 3000L;
		
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3,8,keepAliveTime,TimeUnit.MILLISECONDS,new ArrayBlockingQueue<>(120));
		
		return threadPoolExecutor;
	}

}
