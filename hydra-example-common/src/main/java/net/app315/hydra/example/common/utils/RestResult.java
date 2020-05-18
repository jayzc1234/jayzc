package net.app315.hydra.example.common.utils;

import net.app315.nail.common.result.RichResult;
import org.apache.http.HttpStatus;

/**
 * 自定义统一返回接口定义工具类
 *
 * @author shixiongfei
 * @date 2020-02-17
 * @since
 */
public class RestResult<T>  extends RichResult{

    public static RichResult ok() {
        return new RichResult();
    }

    public static <T> RichResult<T> ok(T t) {
        return new RichResult<>(t);
    }


    public static RichResult error(String errorMsg) {
        return new RichResult(HttpStatus.SC_INTERNAL_SERVER_ERROR, errorMsg);
    }
    public static RichResult success(String msg) {
        return new RichResult(HttpStatus.SC_OK, msg);
    }
    public static RichResult badRequest(String errorMsg) {
        return new RichResult(HttpStatus.SC_BAD_REQUEST, errorMsg);
    }
}