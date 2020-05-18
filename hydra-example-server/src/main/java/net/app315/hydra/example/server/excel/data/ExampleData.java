package net.app315.hydra.example.server.excel.data;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

/**
 * 企业资料excel导入
 *
 * @author shixiongfei
 * @date 2020-02-20
 * @since
 */
@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExampleData extends BaseData {

    @ExcelProperty(value = "姓名")
    private String name;

    @ExcelProperty(value = "手机号码")
    private String telephone;

    @ExcelProperty(value = "角色")
    private String role;

    @ExcelProperty(value = "职务")
    private String job;

    @ExcelProperty(value = "维护问题")
    private String questionTypeName;
}