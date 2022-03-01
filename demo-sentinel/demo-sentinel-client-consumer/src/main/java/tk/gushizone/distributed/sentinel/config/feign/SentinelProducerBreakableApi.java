package tk.gushizone.distributed.sentinel.config.feign;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import tk.gushizone.distributed.sentinel.api.SentinelProducerApi;
import tk.gushizone.distributed.sentinel.config.feign.fallback.SentinelProducerBreakableFallback;

@Qualifier
@Component
//@Component("sentinelProducerBreakableApi")
@FeignClient(name = "demo-sentinel-client-producer", primary = false, fallback = SentinelProducerBreakableFallback.class)
public interface SentinelProducerBreakableApi extends SentinelProducerApi {

}

