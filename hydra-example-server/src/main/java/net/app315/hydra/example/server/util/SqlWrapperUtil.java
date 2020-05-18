package net.app315.hydra.example.server.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.commons.lang3.StringUtils;

/**
 * @author shixiongfei
 * @date 2020-02-17
 * @since
 */
public class SqlWrapperUtil {

    private static final String COL_SYS_ID = "sys_id";

    private static final String COL_ORGANIZATION_ID = "organization_id";

    public static <T> UpdateWrapper<T> wrapper(UpdateWrapper<T> updateWrapper, String sysId, String organizationId) {
        updateWrapper.eq(StringUtils.isNotBlank(sysId), COL_SYS_ID, sysId)
                .eq(StringUtils.isNotBlank(organizationId), COL_ORGANIZATION_ID, organizationId);
        return updateWrapper;
    }

    public static <T> QueryWrapper<T> wrapper(QueryWrapper<T> queryWrapper, String sysId, String organizationId) {
        queryWrapper.eq(StringUtils.isNotBlank(sysId), COL_SYS_ID, sysId)
                .eq(StringUtils.isNotBlank(organizationId), COL_ORGANIZATION_ID, organizationId);
        return queryWrapper;
    }

    public static <T> QueryWrapper<T> wrapper(QueryWrapper<T> queryWrapper, String sysId, String organizationId, String... columns) {
        queryWrapper.select(columns)
                .eq(StringUtils.isNotBlank(sysId), COL_SYS_ID, sysId)
                .eq(StringUtils.isNotBlank(organizationId), COL_ORGANIZATION_ID, organizationId);
        return queryWrapper;
    }
}
