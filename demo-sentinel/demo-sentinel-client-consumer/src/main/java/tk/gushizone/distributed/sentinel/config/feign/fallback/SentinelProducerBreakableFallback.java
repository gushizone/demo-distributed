package tk.gushizone.distributed.sentinel.config.feign.fallback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tk.gushizone.distributed.sentinel.config.feign.SentinelProducerBreakableApi;

@Slf4j
@Component
public class SentinelProducerBreakableFallback implements SentinelProducerBreakableApi {

    @Override
    public String ping() {
        log.warn("降级处理");
        return "触发熔断降级";
    }
}
