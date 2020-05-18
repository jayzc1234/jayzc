package net.app315.hydra.example.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.app315.hydra.example.server.excel.data.ExampleData;
import net.app315.hydra.example.server.mapper.ExampleMapper;
import net.app315.hydra.example.server.model.Example;
import net.app315.hydra.example.server.service.ExampleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author shixiongfei
 * @date 2020-03-30
 * @since
 */
@Service
public class ExampleServiceImpl extends ServiceImpl<ExampleMapper, Example> implements ExampleService {

    @Override
    public void batchAddDataList(List<ExampleData> list) {
        // do nothing, just a example
    }
}
