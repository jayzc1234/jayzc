package net.app315.hydra.example.server.util;

import net.app315.nail.common.utils.LogUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

/**
 * @author shixiongfei
 * @date 2020-02-17
 * @since
 */
public class AppLogUtils {
    /**
     * 提示日志
     *
     * @param logger
     * @param message
     */
    public static void info(Logger logger, String message) {
        if (logger.isInfoEnabled()) {
            logger.info(message);
        }
    }

    /**
     * 提示日志
     *
     * @param logger
     * @param format
     * @param objects
     */
    public static void info(Logger logger, String format, Object... objects) {
        if (logger.isInfoEnabled()) {
            logger.info(format, objects);
        }
    }

    /**
     * 警告日志
     *
     * @param logger
     * @param format
     * @param objects
     */
    public static void warn(Logger logger, String format, Object... objects) {
        if (logger.isWarnEnabled()) {
            if (format == null) {
                format = StringUtils.EMPTY;
            }
            logger.warn("[traceId=" + LogUtil.getLogId() + "]" + format, objects);
        }
    }

    /**
     * 警告日志
     *
     * @param logger
     * @param message
     * @param throwable
     */
    public static void warn(Logger logger, String message, Throwable throwable) {
        if (logger.isWarnEnabled()) {
            if (message == null) {
                message = StringUtils.EMPTY;
            }
            logger.warn("[traceId=" + LogUtil.getLogId() + "]" + message, throwable);
        }
    }

    /**
     * 错误日志
     *
     * @param logger
     * @param format
     * @param objects
     */
    public static void error(Logger logger, String format, Object... objects) {
        if (logger.isErrorEnabled()) {
            if (format == null) {
                format = StringUtils.EMPTY;
            }
            logger.error("[traceId=" + LogUtil.getLogId() + "]" + format, objects);
        }
    }

    /**
     * 错误日志
     *
     * @param logger
     * @param message
     * @param throwable
     */
    public static void error(Logger logger, String message, Throwable throwable) {
        if (logger.isErrorEnabled()) {
            if (message == null) {
                message = StringUtils.EMPTY;
            }
            logger.error("[traceId=" + LogUtil.getLogId() + "]" + message, throwable);
        }
    }
}