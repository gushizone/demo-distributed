package tk.gushizone.distributed.sentinel.config.feign;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import tk.gushizone.distributed.sentinel.api.SentinelProducerApi;
import tk.gushizone.distributed.sentinel.config.feign.fallback.SentinelProducerBreakableFallbackFactory;

@Qualifier
@Component
@FeignClient(name = "demo-sentinel-client-producer", primary = false, fallbackFactory = SentinelProducerBreakableFallbackFactory.class)
public interface SentinelProducerThrowBreakableApi extends SentinelProducerApi {

}

