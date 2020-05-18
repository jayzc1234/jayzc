package net.app315.hydra.example.client;

import io.swagger.annotations.ApiOperation;
import net.app315.hydra.example.common.AppConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description:
 * @Author:         xiaoliang.chen
 * @Date:     2019/2/21 上午11:43
 */
@FeignClient(name = AppConstants.SERVICE_NAME)
public interface ExampleClient {

    @ApiOperation("测试")
    @RequestMapping(value = "test", method = RequestMethod.GET)
    String list();

}
