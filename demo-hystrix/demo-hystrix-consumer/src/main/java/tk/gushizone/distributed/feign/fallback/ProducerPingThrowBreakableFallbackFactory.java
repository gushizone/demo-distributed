package tk.gushizone.distributed.feign.fallback;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tk.gushizone.distributed.feign.ProducerPingThrowBreakableApi;

/**
 * @author gushizone@gmail.com
 * @date 2022/2/25 4:38 下午
 */
@Slf4j
@Component
public class ProducerPingThrowBreakableFallbackFactory implements FallbackFactory<ProducerPingThrowBreakableApi> {
    @Override
    public ProducerPingThrowBreakableApi create(Throwable cause) {
        log.error("触发降级: {}", cause.getMessage());
        return new ProducerPingThrowBreakableApi() {
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
        };
    }
}
