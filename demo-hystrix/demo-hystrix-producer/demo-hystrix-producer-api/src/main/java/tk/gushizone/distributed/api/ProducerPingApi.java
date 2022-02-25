package tk.gushizone.distributed.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author gushizone@gmail.com
 * @date 2022/2/25 2:25 下午
 */
@FeignClient("demo-hystrix-producer")
public interface ProducerPingApi {

    @GetMapping("/ping")
    String ping();

    @GetMapping("/timeout")
    String timeout(@RequestParam("timeout") Long timeout);
}
