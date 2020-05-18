package net.app315.hydra.example.common.utils;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.val;
import org.apache.commons.lang.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;


/**
 * 拓展分页插件类
 *
 * @author shixiongfei
 * @date 2020-02-17
 * @since
 */
@Data
public class DaoSearch extends com.jgw.supercodeplatform.common.pojo.common.DaoSearch {

    @ApiModelProperty(name = "exportMetadata", value = "导出excel的json字段", example = "")
    private String exportMetadata;

    @ApiModelProperty(name = "dataList", value = "导出部分excel数据", example = "")
    private String  dataList;

    @SuppressWarnings("unchecked")
    public Map<String, String> exportMetadataToMap()
    //        throws SuperCodeException
    {
        if (StringUtils.isBlank(exportMetadata)) {
            //throw new SuperCodeException("导出excel类别参数exportMetadata为空：", 500);
        }
        return JSONObject.parseObject(exportMetadata, LinkedHashMap.class);
    }

    @ApiModelProperty(hidden = true)
    public Integer getDefaultCurrent() {
        val current = this.getCurrent();
        if (Objects.isNull(current) || current < 1) {
            return 1;
        }

        return current;
    }

    @ApiModelProperty(hidden = true)
    public Integer getDefaultPageSize() {
        val pageSize = this.getPageSize();
        if (Objects.isNull(pageSize) || pageSize < 1) {
            return 10;
        }
        return pageSize;
    }
}