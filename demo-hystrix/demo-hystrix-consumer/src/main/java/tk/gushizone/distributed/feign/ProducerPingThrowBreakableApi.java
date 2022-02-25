package tk.gushizone.distributed.feign;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import tk.gushizone.distributed.api.ProducerPingApi;
import tk.gushizone.distributed.feign.fallback.ProducerPingThrowBreakableFallbackFactory;

/**
 * @author gushizone@gmail.com
 * @date 2022/2/25 4:37 下午
 */
@Qualifier
@FeignClient(name = "demo-hystrix-producer", primary = false, fallbackFactory = ProducerPingThrowBreakableFallbackFactory.class)
public interface ProducerPingThrowBreakableApi extends ProducerPingApi {
}
