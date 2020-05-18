package net.app315.hydra.example.server.exception;

import com.jgw.supercodeplatform.exception.SuperCodeException;

/**
 * @author shixiongfei
 * @date 2020-02-18
 * @since
 */
public class ExcelException extends SuperCodeException {
    public ExcelException() {
    }

    public ExcelException(String message) {
        super(message);
    }

    public ExcelException(String message, int status) {
        super(message, status);
    }

    public ExcelException(String message, Throwable cause) {
        super(message, cause);
    }
}