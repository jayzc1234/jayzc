package net.app315.hydra.example.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import net.app315.hydra.example.server.components.BeanContextAware;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description:    微服务配置
 * @Author:         xiaoliang.chen
 * @Date:     2019/2/21 上午11:46
 */
@Configuration
@EnableAsync
@EnableSwagger2
@EnableTransactionManagement
public class AppConfig {
    @Bean
    public BeanContextAware beanContextAware() {
        return new BeanContextAware();
    }

    @Bean
    public Executor storeDataAsync() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(8);
        executor.setMaxPoolSize(128);
        executor.setQueueCapacity(8);
        executor.setThreadNamePrefix("Async-Thread");
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

}
