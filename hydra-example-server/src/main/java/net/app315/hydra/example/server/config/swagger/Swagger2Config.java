package net.app315.hydra.example.server.config.swagger;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Description:    Swagger2Config 配置功能模块
 * @Author:         xiaoliang.chen
 * @Date:     2019/8/21 上午11:45
 */
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class Swagger2Config {

    @Value("${swagger2.enable}")
    private boolean enable;

    @Bean("后台模块")
    public Docket rearEndApis() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("后台模块")
                .select()
                .apis(RequestHandlerSelectors.basePackage("net.app315.hydra.human.construction.server.controller.backsystem"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .enable(enable);
    }


    @Bean("小程序模块")
    public Docket smallProgram() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("小程序模块")
                .select()
                .apis(RequestHandlerSelectors.basePackage("net.app315.hydra.human.construction.server.controller.smallprogram"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .enable(enable);
    }

/*    @Bean("首页模块")
    public Docket indexApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("首页模块")
                .select()
                .apis(RequestHandlerSelectors.basePackage("net.app315.hydra.human.construction.server.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .enable(enable);
    }*/

    private ApiInfo apiInfo() {
        Contact contact = new Contact("甲骨文超级码", "http://www.app315.net/", "pubaccount@app315.net");
        return new ApiInfoBuilder()
                .title("项目骨架")
                .description("项目骨架相关接口")
                .termsOfServiceUrl("http://www.app315.net/")
                .contact(contact)
                .version("0.1")
                .build();
    }
}