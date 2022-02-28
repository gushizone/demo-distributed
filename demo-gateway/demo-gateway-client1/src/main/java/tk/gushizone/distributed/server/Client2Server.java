package tk.gushizone.distributed.server;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author gushizone@gmail.com
 * @date 2022/2/28 11:07 上午
 */
@FeignClient("demo-gateway-client2")
public interface Client2Server {

    @GetMapping("/client2/ping")
    String ping();
}
