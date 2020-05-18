package net.app315.hydra.example.common.enums;


import net.app315.nail.common.errcode.BaseErrorCode;

/**
 * @Description:    错误码设置: 9000001分成三段 9 000 001，分别表示：错误级别(9=系统级, 2=服务级)，微服务占用端口号，内部错误码
 * @Author:         xiaoliang.chen
 * @Date:     2019/2/21 上午11:44
 */
public enum ErrorCodeEnum implements BaseErrorCode {
    /** 系统异常 */
    SYSTEM_ERROR(500 , "9000001" , "系统异常"),

    /** 服务异常 */
    SERVICE_ERROR(500 , "2000001" , "服务异常"),
    SERVICE_MQ_PRODUCER_ERROR(500 , "2000002" , "生产者消息队列服务异常"),
    SERVICE_MQ_CONSUMER_ERROR(500 , "2000003" , "消费者消息队列服务异常"),

    /** 参数异常 */
    PARAMS_ERROR(500 , "2000103" , "参数{}不正确"),
    ID_VALUE_INVALID(500 , "2000104" , "参数id的值非法"),
    ;

    private int code;
    private String msg;
    private String internalCode;

    private ErrorCodeEnum(int code, String internalCode, String msg){
        this.code = code;
        this.msg = msg;
        this.internalCode = internalCode;
    }

    @Override
    public int getHttpCode() {
        return code;
    }

    @Override
    public String getErrorMessage() {
        return msg;
    }

    @Override
    public String getInternalErrorCode() {
        return internalCode;
    }
}
