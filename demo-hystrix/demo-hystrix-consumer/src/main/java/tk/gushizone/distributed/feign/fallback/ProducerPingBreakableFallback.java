package tk.gushizone.distributed.feign.fallback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tk.gushizone.distributed.feign.ProducerPingBreakableApi;

/**
 * @author gushizone@gmail.com
 * @date 2022/2/25 4:32 下午
 */
@Slf4j
@Component
public class ProducerPingBreakableFallback implements ProducerPingBreakableApi {


    @Override
    public String ping() {
        log.warn("降级处理");
        return "触发熔断降级";
    }

    @Override
    public String timeout(Long timeout) {
        log.warn("降级处理");
        return "触发熔断降级";
    }
}
