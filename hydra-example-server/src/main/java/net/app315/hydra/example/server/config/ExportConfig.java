package net.app315.hydra.example.server.config;

import org.springframework.context.annotation.Configuration;

/**
 * @author shixiongfei
 * @date 2020-02-18
 * @since
 */
@Configuration
public class ExportConfig {
    //@Value("${common.excel.export-number}")
    public Integer exportNumber = 100000;
}