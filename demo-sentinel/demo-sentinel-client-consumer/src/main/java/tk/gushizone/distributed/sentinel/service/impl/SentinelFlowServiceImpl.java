package tk.gushizone.distributed.sentinel.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;
import tk.gushizone.distributed.sentinel.service.SentinelFlowService;
import tk.gushizone.distributed.sentinel.config.feign.handler.SentinelBlockHandler;

@Service
public class SentinelFlowServiceImpl implements SentinelFlowService {



    @Override
    @SentinelResource(value = "methodResource",
            blockHandlerClass = SentinelBlockHandler.class,
            blockHandler = "voidBlock")
    public void doSomething() {
        // ...
    }
}
