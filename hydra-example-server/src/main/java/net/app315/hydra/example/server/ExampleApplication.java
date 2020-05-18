package net.app315.hydra.example.server;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Description:    微服务入口
 * @Author:         xiaoliang.chen
 * @Date:     2019/2/21 上午11:59
 */
@Slf4j
@EnableFeignClients(basePackages = {"net.app315.hydra.example.client"})
@EnableTransactionManagement
@SpringBootApplication
@EnableApolloConfig
@MapperScan(basePackages = {"com.jgw.supercodeplatform.interceptor.dao"})
@ComponentScan(basePackages = {"net.app315.hydra", "com.jgw.supercodeplatform"})
public class ExampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
    }
}