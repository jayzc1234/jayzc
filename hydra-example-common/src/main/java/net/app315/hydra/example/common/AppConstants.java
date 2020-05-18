package net.app315.hydra.example.common;

/**
 * @Description: 微服务常量
 * @Author: xiaoliang.chen
 * @Date: 2019/2/21 下午3:33
 */
public interface AppConstants {
    String SERVICE_NAME = "hydra-example";

    String VERSION = "v1";

    String PATH = SERVICE_NAME + "/" + "api" + "/" + VERSION + "/";

    /**
     * 无需登录校验的API
     */
    String PATH_NOT_VALID = "/" + PATH + "**";

    /**
     * 无需登录校验的API前缀
     */
    String PATH_NOT_VALID_PREFIX = "/" + PATH + "/";

    /**
     * redis中使用的key前缀（不用的微服务请保持不同，建议使用微服务名）
     */
    String REDIS_PREFIX = "hydraHumanConstruction:";

    /**
     * 走平台中心的接口API统一前缀
     */
    String VALID_PREFIX = "/valid";

    String PATH_VALID = VALID_PREFIX + "/" + PATH ;
}