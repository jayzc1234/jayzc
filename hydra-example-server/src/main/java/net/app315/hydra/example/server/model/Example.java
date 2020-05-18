package net.app315.hydra.example.server.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author shixiongfei
 * @since 2020-03-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_example")
public class Example implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 资源主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 手机号
     */
    @TableField("telephone")
    private String telephone;

    /**
     * 1会员，2民建
     */
    @TableField("ascription_type")
    private Integer ascriptionType;

    @TableField("business_id")
    private Integer businessId;

    /**
     * 系统id
     */
    @TableField(value = "sys_id", fill = FieldFill.INSERT)
    private String sysId;

    /**
     * 组织id
     */
    @TableField(value = "organization_id", fill = FieldFill.INSERT)
    private String organizationId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 创建人id
     */
    @TableField(value = "operator", fill = FieldFill.INSERT)
    private String operator;

    /**
     * 创建人名称
     */
    @TableField(value = "operator_name", fill = FieldFill.INSERT)
    private String operatorName;


    public static final String COL_ID = "id";

    public static final String COL_TELEPHONE = "telephone";

    public static final String COL_ASCRIPTION_TYPE = "ascription_type";

    public static final String COL_BUSINESS_ID = "business_id";

    public static final String COL_SYS_ID = "sys_id";

    public static final String COL_ORGANIZATION_ID = "organization_id";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_OPERATOR = "operator";

    public static final String COL_OPERATOR_NAME = "operator_name";

}
