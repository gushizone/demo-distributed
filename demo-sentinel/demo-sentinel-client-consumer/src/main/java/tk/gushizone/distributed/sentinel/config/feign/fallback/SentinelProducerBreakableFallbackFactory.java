package tk.gushizone.distributed.sentinel.config.feign.fallback;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tk.gushizone.distributed.sentinel.config.feign.SentinelProducerThrowBreakableApi;

@Slf4j
@Component
public class SentinelProducerBreakableFallbackFactory implements FallbackFactory<SentinelProducerThrowBreakableApi> {

    @Override
    public SentinelProducerThrowBreakableApi create(Throwable throwable) {
        if (throwable instanceof BlockException && !(throwable instanceof DegradeException)) {
            BlockException blockException = (BlockException) throwable;
            log.error("触发流控降级，资源名: {}", blockException.getRule().getResource());
        } else if (throwable instanceof DegradeException) {
            DegradeException degradeException = (DegradeException) throwable;
            log.error("触发熔断降级，资源名: {}", degradeException.getRule().getResource());
        } else {
            log.error("触发降级: {}", throwable.getMessage());
        }
        return new SentinelProducerThrowBreakableApi() {
            @Override
            public String ping() {
                log.warn("降级处理");
                return "触发降级";
            }
        };
    }
}
