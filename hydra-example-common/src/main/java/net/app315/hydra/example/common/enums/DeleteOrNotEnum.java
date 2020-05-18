package net.app315.hydra.example.common.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

/**
 * 是否被删除枚举类
 *
 * @author shixiongfei
 * @date 2020-02-18
 * @since
 */
@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum DeleteOrNotEnum {

    /** 未删除 */
    NOT_DELETED(0, "未删除"),

    /** 已删除 */
    DELETED(1, "已删除");

    int key;

    String value;
}