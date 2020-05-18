package net.app315.hydra.example.server.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.jgw.supercodeplatform.model.InterceptorEmployee;
import com.jgw.supercodeplatform.user.UserInfoUtil;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import net.app315.hydra.example.common.enums.DeleteOrNotEnum;
import net.app315.hydra.example.common.utils.IdWorkerUtil;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

/**
 * 自定义字段数据自动填充处理器
 *
 * @author shixiongfei
 * @since 2019-08-20
 */
@Slf4j
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MyMetaObjectHandler implements MetaObjectHandler {

    UserInfoUtil userInfoUtil;

    /**
     * 新增时进行数据填充，作用于{@code FieldFill.INSERT 和 FieldFill.INSERT_UPDATE}
     * setInsertFieldValByName()方法和setFieldValByName()方法的区别在于setInsertFieldValByName()
     * 方法中添加了一个isFill()校验，校验方式如下：
     * 填充判断
     * <li> 如果是主键,不填充 </li>
     * <li> 根据字段名找不到字段,不填充 </li>
     * <li> 字段类型与填充值类型不匹配,不填充 </li>
     * <li> 字段类型需在TableField注解里配置fill: @TableField(value="test_type", fill = FieldFill.INSERT), 没有配置或者不匹配时不填充 </li>
     * v_3.1.0以后的版本(不包括3.1.0)，子类的值也可以自动填充，Timestamp的值也可以填入到java.util.Date类型里面（当前版本为3.1.0，因此不支持子类的值进行自动填充）
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");

        Date now = new Date();

        // 起始版本 3.3.0(推荐使用)
        // 新增创建时间
        this.strictInsertFill(metaObject, "createTime", Date.class, now);

        // 新增修改时间
        this.strictInsertFill(metaObject, "updateTime", Date.class, now);

        // 添加删除状态为未删除
        this.strictInsertFill(metaObject, "deleted", Integer.class, DeleteOrNotEnum.NOT_DELETED.getKey());

        // 添加分布式自增id
        this.strictInsertFill(metaObject, "userId", String.class, IdWorkerUtil.getId());

        try {
            // 新增系统id
            this.strictInsertFill(metaObject, "sysId", String.class, userInfoUtil.getSysId());

            // 新增组织id
            this.strictInsertFill(metaObject, "organizationId", String.class, userInfoUtil.getOrganizationId());

            InterceptorEmployee employee = userInfoUtil.getEmployeeInfo();

            String employeeId = employee.getEmployeeId();
            String employeeName = employee.getEmployeeName();

            // 新增创建人id
            this.strictInsertFill(metaObject, "operator", String.class, employeeId);

            // 新增创建人名称
            this.strictInsertFill(metaObject, "operatorName", String.class, employeeName);
        } catch (Exception e) {
            log.error("自动插入信息失败", e);
        }
    }

    /**
     * 更新时进行数据填充，作用于{@code FieldFill.UPDATE 和 FieldFill.INSERT_UPDATE}
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        // 修改更新时间
        this.strictInsertFill(metaObject, "updateTime", Date.class, new Date());
    }
}