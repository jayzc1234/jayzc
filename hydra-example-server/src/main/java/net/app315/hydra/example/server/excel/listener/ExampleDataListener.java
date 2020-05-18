package net.app315.hydra.example.server.excel.listener;

import com.alibaba.excel.context.AnalysisContext;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import net.app315.hydra.example.server.excel.data.ExampleData;
import net.app315.hydra.example.server.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author shixiongfei
 * @date 2020-02-20
 * @since
 */
@Slf4j
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExampleDataListener extends BaseReadListener<ExampleData> {

    @Autowired
    private ExampleService exampleService;

    @Override
    public void invoke(ExampleData data, AnalysisContext context) {
        super.invoke(data, context);
        list.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            exampleService.batchAddDataList(list);
            // 存储完成清理 list
            list.clear();
        }

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        super.doAfterAllAnalysed(context);
        if (list.size() > 0) {
            exampleService.batchAddDataList(list);
            // 存储完成清理 list
            list.clear();
        }
    }

}