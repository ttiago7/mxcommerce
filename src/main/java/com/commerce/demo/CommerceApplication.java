package com.commerce.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@Configuration
@EnableAsync
public class CommerceApplication {

    @Bean("threadPoolExecutor")
    public TaskExecutor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(20);
        executor.setMaxPoolSize(40);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        //executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("Async-");
        //executor.initialize();
        return executor;
    }

    public static void main(String[] args) {
        SpringApplication.run(CommerceApplication.class, args);
    }

}
