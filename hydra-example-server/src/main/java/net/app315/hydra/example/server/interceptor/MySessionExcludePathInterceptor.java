package net.app315.hydra.example.server.interceptor;

import com.jgw.supercodeplatform.web.WebMvcSessionInterceptorPathConfigurer;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @Description:    拦截器
 * @Author:         xiaoliang.chen
 * @Date:     2019/8/21 上午8:47
 */
@Component
public class MySessionExcludePathInterceptor implements WebMvcSessionInterceptorPathConfigurer {
    @Override
    public List<String> excludePathPatterns() {
        String[] add = new String[]{
                //swagger
                "/swagger-resources/configuration/ui",
                "/swagger-resources",
                "/swagger-resources/configuration/security",
                "/swagger-ui.html",
                "/v2/**",
                "/doc.html",
                "/productmanage/harvest/damage/hi",
                //静态资源
                "/webjars/**"
        };
        return Arrays.asList(add);
    }
}
