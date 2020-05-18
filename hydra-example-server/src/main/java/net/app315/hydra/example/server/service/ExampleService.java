package net.app315.hydra.example.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.app315.hydra.example.server.excel.data.ExampleData;
import net.app315.hydra.example.server.model.Example;

import java.util.List;

/**
 * @author shixiongfei
 * @date 2020-03-30
 * @since
 */
public interface ExampleService extends IService<Example> {

    /**
     * 用例
     * @param list
     */
    void batchAddDataList(List<ExampleData> list);
}
