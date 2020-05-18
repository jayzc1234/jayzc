package net.app315.hydra.example.common.utils;

import com.jgw.supercodeplatform.common.pojo.common.Page;

/**
 * @author shixiongfei
 * @date 2020-02-17
 * @since
 */
public class PageUtils {

    public static Page page(long pageSize, long current, long total) {
        return new Page((int) pageSize, (int) current, (int) total);
    }
}
