package net.app315.hydra.example.server.config;

import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:    feign调用配置
 * @Author:         xiaoliang.chen
 * @Date:     2019/2/21 上午11:47
 */
@Configuration
public class FeignConfigure {
    public static int connectTimeOutMillis = 12000;
    public static int readTimeOutMillis = 12000;
    @Bean
    public Request.Options options() {
        return new Request.Options(connectTimeOutMillis, readTimeOutMillis);
    }
}
