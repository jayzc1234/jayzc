package net.app315.hydra.example.common.utils;

import com.jgw.supercodeplatform.exception.SuperCodeExtException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * 通用异常抛出工具类
 *
 * @author shixiongfei
 * @date 2020-02-23
 * @since
 */
public interface CommonExUtil {

    /**
     * 为null则抛出异常
     *
     * @param
     * @return
     * @author shixiongfei
     * @date 2020-02-23
     * @updateDate 2020-02-23
     * @updatedBy shixiongfei
     */
    static void null2Ex(Object obj, String errorMsg) {
        if (Objects.isNull(obj)) {
            throw new SuperCodeExtException(errorMsg);
        }
    }

    /**
     * 集合不为空则抛出异常
     *
     * @param
     * @return
     * @author shixiongfei
     * @date 2020-02-23
     * @updateDate 2020-02-23
     * @updatedBy shixiongfei
     */
    static void notEmpty2Ex(List list, String errorMsg) {
        if (CollectionUtils.isNotEmpty(list)) {
            throw new SuperCodeExtException(errorMsg);
        }
    }

    /**
     * 集合为空则抛出异常
     *
     * @param
     * @return
     * @author shixiongfei
     * @date 2020-02-23
     * @updateDate 2020-02-23
     * @updatedBy shixiongfei
     */
    static void empty2Ex(List list, String errorMsg) {
        if (CollectionUtils.isEmpty(list)) {
            throw new SuperCodeExtException(errorMsg);
        }
    }

    /**
     * 大于指定的目标值，则抛出异常
     *
     * @param
     * @return
     * @author shixiongfei
     * @date 2020-02-23
     * @updateDate 2020-02-23
     * @updatedBy shixiongfei
     */
    static void greaterThanTarget2Ex(int count, int targetCount, String errorMsg) {
        if (count > targetCount) {
            throw new SuperCodeExtException(errorMsg);
        }
    }

    /**
     * count大于0则抛出异常
     *
     * @param
     * @return
     * @author shixiongfei
     * @date 2020-02-23
     * @updateDate 2020-02-23
     * @updatedBy shixiongfei
     */
    static void greaterThanZero2Ex(int count, String errorMsg) {
        greaterThanTarget2Ex(count, 0, errorMsg);
    }

    /**
     * 字符串为null或''或' '则抛出异常
     *
     * @param
     * @return
     * @author shixiongfei
     * @date 2020-02-23
     * @updateDate 2020-02-23
     * @updatedBy shixiongfei
     */
    static void blank2Ex(String str, String errorMsg) {
        if (StringUtils.isBlank(str)) {
            throw new SuperCodeExtException(errorMsg);
        }
    }

    /**
     * 直接抛出异常
     *
     * @param
     * @return
     * @author shixiongfei
     * @date 2020-02-23
     * @updateDate 2020-02-23
     * @updatedBy shixiongfei
     */
    static void ex(String errorMsg) {
        throw new SuperCodeExtException(errorMsg);
    }

    /**
     * count为0则抛出异常
     *
     * @param
     * @return
     * @author shixiongfei
     * @date 2020-02-27
     * @updateDate 2020-02-27
     * @updatedBy shixiongfei
     */
    static void zero2Ex(int count, String errorMsg) {
        if (count == 0) {
            throw new SuperCodeExtException(errorMsg);
        }
    }

    /**
     * 非正常数字报错，正常数字代表 1.类型为数字类型 2. 数字 > 0
     *
     * @param
     * @return
     * @author shixiongfei
     * @date 2020-03-02
     * @updateDate 2020-03-02
     * @updatedBy shixiongfei
     */
    static void notNumber2Ex(Number questionId, String errorField) {
        if (Objects.isNull(questionId) || questionId.intValue() < 1) {
            throw new SuperCodeExtException(String.format("%s格式错误", errorField));
        }
    }

    /**
     * 条件为true则报错
     *
     * @author shixiongfei
     * @date 2020-03-02
     * @updateDate 2020-03-02
     * @updatedBy shixiongfei
     * @param
     * @return
     */
    static void true2Ex(boolean isTrue, String errorMsg) {
        if (isTrue) {
            throw new SuperCodeExtException(errorMsg);
        }
    }
}